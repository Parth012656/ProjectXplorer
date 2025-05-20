import React, { useState, useEffect, useRef } from "react";
import "../Styling/GeminiChat.css"; 

const API_KEY = "AIzaSyBvCzfM2OtZYnN-Es_OlUQJUFjHktc2dps";

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
    <div className="gemini-chat">
      {!showChat ? (
        <button className="chat-icon" onClick={() => setShowChat(true)}>💬</button>
      ) : (
        <div className="chat-box">
          <div className="chat-header">
            <h4>Propy Chat</h4>
            <button onClick={() => setShowChat(false)}>❌</button>
          </div>
          <div className="chat-messages" ref={chatBoxRef}>
            {messages.map((msg, index) => (
              <p key={index} className={msg.sender === "user" ? "user-message" : "bot-message"}>
                <strong>{msg.sender === "user" ? "You" : "Propy"}:</strong>
                <span dangerouslySetInnerHTML={{ __html: msg.text }} />
              </p>
            ))}
            {loading && <p className="bot-message">⏳ Generating response...</p>}
          </div>
          <div className="chat-input">
            <input
              type="text"
              value={input}
              onChange={(e) => setInput(e.target.value)}
              placeholder="Type a message..."
              disabled={loading}
              onKeyDown={(e) => e.key === "Enter" && sendMessage()} 
            />
            <button onClick={sendMessage} disabled={loading}>Send</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default GeminiChat;