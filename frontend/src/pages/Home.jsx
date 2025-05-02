import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom'

function Home() {
    const [name, setName] = useState('');
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    
    useEffect(() => {
        const link = document.createElement('link');
        link.rel = 'stylesheet';
        link.href = '/styles/Home.css'; // Ensure correct path to your CSS file
        document.head.appendChild(link);
    
        // Cleanup on unmount
        return () => {
          document.head.removeChild(link);
        };
    }, []);



    return (
    <>
        <div className="container">
            <div>
                <h1>Welcome to the Course Management System</h1>
            </div>
            <div><h1>Select Options</h1></div>

            <div className='button-container'>
                <button onClick={() => navigate('/student-list')}>Student List</button>
                <button onClick={() => navigate('/delete-course')}>Delete Course</button>
                <button onClick={() => navigate('/list-all')}>List All</button>
            </div>
        </div>
    </>
    );
}

export default Home;