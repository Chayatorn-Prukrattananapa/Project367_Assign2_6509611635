import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function DeleteCourse() {
    const [subjectId, setSubjectId] = useState('');
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    useEffect (() => {
        const link = document.createElement('link');
        link.rel = 'stylesheet';
        link.href = '/styles/DeleteCourse.css';
        document.head.appendChild(link);
      });

    const deleteCourse = async (e) => {
        e.preventDefault();
        let list = [];
        try {
            const response = await fetch(`http://localhost:2025/api/subject/${subjectId}`, {
                method: 'DELETE',
            });
            if (response.ok) {
                setMessage('Course deleted successfully');
            } else if (response.status === 404) {
                setMessage('Course not found');
            } else {
                setMessage(`Failed to delete course`);
            }
        } catch (error) {
            console.error('Error deleting course');
            setMessage('Failed to delete course');
        }
    };

    return (
        <>
        <div className='container'>
            <div>
                
                <h1>Delete Course</h1>

                <form id="delete-course-form" onSubmit={deleteCourse}>
                    <label htmlFor="courseId">Course ID:</label>
                    <input
                        type="text"
                        name="subjectId"
                        onChange={(e) => setSubjectId(e.target.value)}
                        placeholder="Course ID"
                        required
                        />
                    <button type="submit">Delete</button>
                </form>
            </div>

            <div className='button-container'>
                <button onClick={() => navigate('/')}>Back</button>
            </div>
        </div>
            {message && <h2>{message}</h2>}
        </>
    );
}

export default DeleteCourse;