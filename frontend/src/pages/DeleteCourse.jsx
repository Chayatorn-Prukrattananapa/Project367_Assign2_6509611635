import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function DeleteCourse() {
    const [students, setStudents] = useState([]);
    const [subjectId, setSubjectId] = useState('');
    const [message, setMessage] = useState('');
    const [courseLoaded, setCourseLoaded] = useState(false);
    const navigate = useNavigate();

    useEffect (() => {
        const link = document.createElement('link');
        link.rel = 'stylesheet';
        link.href = '/styles/DeleteCourse.css';
        document.head.appendChild(link);
      }, []);

      const deleteCourse = async (e) => {
        e.preventDefault();
    
        // First: Fetch student data
        try {
            const response = await fetch(`http://localhost:2025/api/subject/list/${subjectId}`, {
                method: 'GET',
            });
    
            if (!response.ok) {
                throw new Error('Failed to fetch student list');
            }
    
            const data = await response.json();
            setStudents(data);
            setCourseLoaded(true); // Optional flag if needed later
        } catch (error) {
            console.error('Error fetching students:', error);
            setMessage('Failed to fetch student list');
            return; // Do not proceed to DELETE if GET fails
        }
    
        // Second: Delete the course
        try {
            const response = await fetch(`http://localhost:2025/api/subject/${subjectId}`, {
                method: 'DELETE',
            });
    
            if (response.ok) {
                setMessage('Course deleted successfully');
            } else {
                setMessage('Failed to delete course');
            }
        } catch (error) {
            console.error('Error deleting course:', error);
            setMessage('Failed to delete course');
        }
    };
    

    const renderDeletedList = (students) => {
        if (!students.length) {
          return <h2>No students found in this course.</h2>;
        }
      
        return (
          <table className="student-table">
            <thead>
              <tr>
                <th>Student ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Major</th>
                <th>Minor</th>
                <th>Faculty</th>
                <th>Year</th>
              </tr>
            </thead>
            <tbody>
              {students.map((student, index) => (
                <tr key={index}>
                  <td>{student.studentId}</td>
                  <td>{student.firstName}</td>
                  <td>{student.lastName}</td>
                  <td>{student.major}</td>
                  <td>{student.minor}</td>
                  <td>{student.faculty}</td>
                  <td>{student.year}</td>
                </tr>
              ))}
            </tbody>
          </table>
        );
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
            {message === 'Course deleted successfully' && 
            (<div className='table-container'>
                <h2>Deleted {students.length} student(s)</h2>
                {renderDeletedList(students)}</div>)}
        </>
    );
}

export default DeleteCourse;