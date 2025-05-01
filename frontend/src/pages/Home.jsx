import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import StudentList from './StudentList'; 
const Home = () => {
    const [name, setName] = useState('');
    const navigate = useNavigate();

    return (
    <>
        <div><h1>Select Options</h1></div>
        
        <div>
            <div className="button" onClick={navigate('/student-list')}>Student List</div>
            <div className="button" onClick={navigate('/delete-course')}>Delete Course</div>
        </div>

    </>
    );
}

export default Home;