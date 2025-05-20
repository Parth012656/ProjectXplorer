import React, { useState } from "react";
import "../Styling/Card.css";
import ProjectModal from "./ProjectModal.jsx";
import { useFavorites } from "./FavoritesContext";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart } from '@fortawesome/free-solid-svg-icons';

function Card({ projects }) {
  const [selectedProject, setSelectedProject] = useState(null);
  const { favorites, addToFavorites } = useFavorites();

  const isFavorited = (project) => {
    return favorites.some((fav) => fav.pId === project.pId);
  };

  return (
    <div className="projects-container">
      {projects.length > 0 ? (
        projects.map((project) => (
          <div
            key={project.pId}
            className="project-card"
            onClick={() =>
              selectedProject && selectedProject.pId === project.pId
                ? setSelectedProject(null)
                : setSelectedProject(project)
            }
          >
            <div className="card-header">
              <h3 className="project-title">{project.pName}</h3>
              <button
                className={`favorite-btn ${isFavorited(project) ? "favorited" : ""}`}
                onClick={(e) => {
                  e.stopPropagation();
                  addToFavorites(project);
                }}
              >
                <FontAwesomeIcon icon={faHeart} />
              </button>
            </div>

            <p className="project-description">{project.briefDes}</p>

            <p className="project-difficulty">
              Difficulty:{" "}
              {project.diffLevel === 1
                ? "Beginner"
                : project.diffLevel === 2
                ? "Intermediate"
                : "Advanced"}
            </p>

            <p className="project-rating">
              Rating: {"⭐".repeat(project.rating)}
            </p>

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
          </div>
        ))
      ) : (
        <p className="no-projects-message">No projects found.</p>
      )}

      {selectedProject && (
        <ProjectModal
          project={selectedProject}
          onClose={() => setSelectedProject(null)}
        />
      )}
    </div>
  );
}

export default Card;
