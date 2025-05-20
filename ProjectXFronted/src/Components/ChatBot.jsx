import React, { useState, useEffect, useRef } from "react";

const API_KEY = import.meta.env.VITE_GEMINI_API_KEY;

function formatResponse(text) {
  return text
      .replace(/\*\*(.*?)\*\*/g, "<strong>$1</strong>")
      .replace(/\*(.*?)\*/g, "<em>$1</em>")
      .replace(/\n{2,}/g, "<br><br>")
      .replace(/\n/g, "<br>")
      .replace(/```(.*?)```/gs, "<pre><code>$1</code></pre>")
      .replace(/`(.*?)`/g, "<code>$1</code>");
}

const GeminiChat = () => {
  const [showChat, setShowChat] = useState(false);
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState("");
  const [loading, setLoading] = useState(false);
  const chatBoxRef = useRef(null);

  useEffect(() => {
    if (chatBoxRef.current) {
      chatBoxRef.current.scrollTop = chatBoxRef.current.scrollHeight;
    }
  }, [messages]);

  const sendMessage = async () => {
    if (!input.trim() || loading) return;
    setLoading(true);
    const newMessages = [...messages, { text: input, sender: "user" }];
    setMessages(newMessages);
    setInput("");
  
    try {
      const response = await fetch(
        `https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=${API_KEY}`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ contents: [{ parts: [{ text: input }] }] }),
        }
      );
  
      const data = await response.json();
      const responseParts = data?.candidates?.[0]?.content?.parts || [];
      const responseText = responseParts.map(part => part.text).join(" ") || "⚠️ No valid response from AI.";
      const formattedResponse = formatResponse(responseText);
  
      setMessages([...newMessages, { text: formattedResponse, sender: "bot" }]);
    } catch (error) {
      console.error("Error fetching response:", error);
      setMessages([...newMessages, { text: "⚠️ API Error: Unable to fetch response.", sender: "bot" }]);
    } finally {
      setLoading(false);
    }
  };
  

  return (
    <div className="fixed bottom-4 right-4 z-50">
      {!showChat ? (
        <button
          className="p-3 bg-blue-600 text-white rounded-full shadow-lg hover:bg-blue-700 transition"
          onClick={() => setShowChat(true)}
        >
          💬
        </button>
      ) : (
        <div className="w-1/3 h-3/4 bg-white shadow-2xl rounded-lg flex flex-col fixed bottom-4 right-4 border border-gray-200">
          <div className="flex justify-between items-center bg-blue-600 text-white p-3 rounded-t-lg">
            <h4>Propy Chat</h4>
            <button onClick={() => setShowChat(false)} className="text-white hover:text-gray-300">❌</button>
          </div>
          <div className="flex-1 p-4 overflow-y-auto" ref={chatBoxRef}>
            {messages.map((msg, index) => (
              <p key={index} className={`p-2 rounded-lg my-1 ${msg.sender === "user" ? "bg-blue-100 self-end" : "bg-gray-200 self-start"}`}>
                <strong>{msg.sender === "user" ? "You" : "Propy"}:</strong>
                <span dangerouslySetInnerHTML={{ __html: msg.text }} />
              </p>
            ))}
            {loading && <p className="text-gray-500">⏳ Generating response...</p>}
          </div>
          <div className="flex p-3 border-t border-gray-300">
            <input
              type="text"
              value={input}
              onChange={(e) => setInput(e.target.value)}
              placeholder="Type a message..."
              className="flex-1 p-2 border rounded-lg"
              disabled={loading}
              onKeyDown={(e) => e.key === "Enter" && sendMessage()}
            />
            <button onClick={sendMessage} disabled={loading} className="ml-2 bg-blue-600 text-white p-2 rounded-lg hover:bg-blue-700 transition">
              Send
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default GeminiChat;