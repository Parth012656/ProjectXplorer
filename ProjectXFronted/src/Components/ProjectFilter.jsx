import React, { useState } from "react";
import Card from "./Card.jsx";
import "../Styling/FilterComponent.css";
import GeminiChat from "./GeminiChat.jsx";

const FilterComponent = () => {
  const [projects, setProjects] = useState([]);
  const [filters, setFilters] = useState({
    area: "",
    difficulty: "",
    rating: "",
  });
  const [error, setError] = useState(null);

  const fetchProjects = async () => {
    let url = "http://localhost:8080/projectFilter";
    const params = new URLSearchParams();

    const { area, difficulty, rating } = filters;

    if (area && difficulty && rating) {
      url += "/search";
      params.append("area", area);
      params.append("difficulty", parseInt(difficulty));
      params.append("rating", parseInt(rating));
    } else if (area && difficulty) {
      url += "/searchAD";
      params.append("area", area);
      params.append("difficulty", parseInt(difficulty));
    } else if (area && rating) {
      url += "/searchRA";
      params.append("area", area);
      params.append("rating", parseInt(rating));
    } else if (difficulty && rating) {
      url += "/searchDR";
      params.append("difficulty", parseInt(difficulty));
      params.append("rating", parseInt(rating));
    } else if (rating) {
      url += "/searchR";
      params.append("rating", parseInt(rating));
    } else if (difficulty) {
      url += "/searchD";
      params.append("difficulty", parseInt(difficulty));
    } else if (area) {
      url += "/searchA";
      params.append("area", area);
    } else {
      url += "/search";
    }

    if (params.toString()) {
      url += `?${params.toString()}`;
    }

    try {
      const response = await fetch(url);
      if (!response.ok) throw new Error("Failed to fetch projects");
      const data = await response.json();

      if (!Array.isArray(data)) throw new Error("Invalid API response");

      setProjects(data);
      setError(null);
    } catch (error) {
      console.error("Error fetching projects:", error);
      setError("Failed to fetch projects. Please check your filters and try again.");
      setProjects([]);
    }
  };

  const handleChange = (e) => {
    setFilters({ ...filters, [e.target.name]: e.target.value });
  };

  return (
    <div className="filter-container">
      <div className="filter-controls">
        <select name="area" onChange={handleChange} className="filter-select">
          <option value="">Select Field</option>
          <option value="1">AI/ML</option>
          <option value="3">Web Development</option>
          <option value="2">IoT</option>
          <option value="4">Game Development</option>
          <option value="5">App Development</option>
        </select>

        <select name="difficulty" onChange={handleChange} className="filter-select">
          <option value="">Select Difficulty</option>
          <option value="1">Beginner</option>
          <option value="2">Intermediate</option>
          <option value="3">Advanced</option>
        </select>

        <select name="rating" onChange={handleChange} className="filter-select">
          <option value="">Select Rating</option>
          {[1, 2, 3, 4, 5].map((num) => (
            <option key={num} value={num}>{num}</option>
          ))}
        </select>

        <button onClick={fetchProjects} className="filter-button">Apply Filters</button>
      </div>

      {error && <p className="error-message">{error}</p>}

      <Card projects={projects} />
      <div className='GeminiChat'>
        <GeminiChat />
      </div>
    </div>
  );
};

export default FilterComponent;
