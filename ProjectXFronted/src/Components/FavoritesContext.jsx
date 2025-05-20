import React, { createContext, useContext, useState } from 'react';

const FavoritesContext = createContext();

export const useFavorites = () => useContext(FavoritesContext);

export const FavoritesProvider = ({ children }) => {
  const [favorites, setFavorites] = useState([]);

  const addToFavorites = (project) => {
    setFavorites((prev) => {
      const isAlreadyFavorited = prev.some((fav) => fav.pId === project.pId);
      if (isAlreadyFavorited) {
        return prev.filter((fav) => fav.pId !== project.pId); 
      } else {
        return [...prev, project]; 
      }
    });
  };
  

  const removeFromFavorites = (pId) => {
    setFavorites((prev) => prev.filter((item) => item.pId !== pId));
  };

  return (
    <FavoritesContext.Provider value={{ favorites, addToFavorites, removeFromFavorites }}>
      {children}
    </FavoritesContext.Provider>
  );
};
