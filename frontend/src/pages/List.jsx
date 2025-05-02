import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function List () {
    useEffect(() => {
        const link = document.createElement('link');
        link.rel = 'stylesheet';
        link.href = '/styles/List.css';
        document.head.appendChild(link);
  
        // Cleanup on unmount
        return () => {
          document.head.removeChild(link);
        };
    }, []);
}