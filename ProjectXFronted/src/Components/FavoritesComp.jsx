import React, { useState } from "react";
import "../Styling/Fav.css";
import { useFavorites } from "./FavoritesContext";
import ProjectModal from "./ProjectModal.jsx";

function FavoritesComp() {
  const { favorites, removeFromFavorites } = useFavorites();
  const [selectedProject, setSelectedProject] = useState(null);

  return (
    <div className="favorites-container">
      {favorites.length > 0 ? (
        favorites.map((project) => (
          <div
            key={project.pId}
            className="favorite-card"
            onClick={() => setSelectedProject(project)} 
          >
            <h3 className="project-title">{project.pName}</h3>
            <p className="project-description">{project.briefDes}</p>
            <p className="project-difficulty">
              Difficulty:{" "}
              {project.diffLevel === 1
                ? "Beginner"
                : project.diffLevel === 2
                ? "Intermediate"
                : "Advanced"}
            </p>
            <p className="project-rating">Rating: {"⭐".repeat(project.rating)}</p>
            <p className="project-domain">
              Domain:{" "}
              {project.domain === "AI/ML"
                ? "Artificial Intelligence & Machine Learning"
                : project.domain === "IoT"
                ? "Internet of Things"
                : project.domain === "Web Development"
                ? "Web Development"
                : project.domain === "Game Development"
                ? "Game Development"
                : "App Development"}
            </p>

            <button
              className="remove-btn"
              onClick={(e) => {
                e.stopPropagation(); 
                removeFromFavorites(project.pId);
              }}
            >
              ❌ Remove
            </button>
          </div>
        ))
      ) : (
        <p className="no-favorites-message">No favorite projects yet.</p>
      )}

      {}
      {selectedProject && (
        <ProjectModal
          project={selectedProject}
          onClose={() => setSelectedProject(null)}
        />
      )}
    </div>
  );
}

export default FavoritesComp;
