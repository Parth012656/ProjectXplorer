import React, { useEffect } from "react";
import "../Styling/ProjectModal.css";

const ProjectModal = ({ project, onClose }) => {
  if (!project) return null;

  
  const handleOverlayClick = (e) => {
    if (e.target.classList.contains("modal-overlay")) {
      onClose();
    }
  };

  
  useEffect(() => {
    const handleKeyDown = (e) => {
      if (e.key === "Escape") {
        onClose();
      }
    };
    window.addEventListener("keydown", handleKeyDown);
    return () => window.removeEventListener("keydown", handleKeyDown);
  }, [onClose]);

  return (
    <div className="modal-overlay" onClick={handleOverlayClick}>
      <div className="modal-content">
        <h2>{project.pName}</h2>

        <div className="info-box">
          <strong>Description:</strong>
          <p>{project.description?.wDescription}</p>
        </div>

        <div className="info-box">
          <strong>Best Technologies:</strong>
          <p>{project.description?.bestTech}</p>
        </div>

        <div className="info-box">
          <strong>Software Required:</strong>
          <p>{project.description?.softReq}</p>
        </div>

        <div className="info-box">
          <strong>Hardware Required:</strong>
          <p>{project.description?.hardReq}</p>
        </div>

        <button className="close-button" onClick={onClose}>Close</button>
      </div>
    </div>
  );
};

export default ProjectModal;
