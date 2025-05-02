import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function StudentList() {


    const [subjectId, setSubjectId] = useState("");
    const [students, setStudents] = useState([]);
    const [searchPerformed, setSearchPerformed] = useState(false);
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
      const link = document.createElement('link');
      link.rel = 'stylesheet';
      link.href = '/styles/StudentList.css'; // Ensure correct path to your CSS file
      document.head.appendChild(link);
  
      // Cleanup on unmount
      return () => {
        document.head.removeChild(link);
      };
    }, []);
    

    const searchStudent = async (e) => {
      e.preventDefault();
      setLoading(true);
      setSearchPerformed(true);
    
      try {
        console.log("Fetching students for subject ID:", subjectId);
        // Fetch students from the backend API
        const response = await fetch(`http://localhost:2025/api/subject/list/${subjectId}`, {
          method: 'GET',
          
        });
        if (!response.ok) throw new Error("Network response was not ok");
    
        const data = await response.json();
        setStudents(data);
        console.log("Fetched students:", data);
      } catch (error) {
        console.error("Error fetching students:", error);
        setStudents([]);
      } finally {
        setLoading(false);
      }
    };

    const showStudentInfo = (student) => {
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
                      { students.map((student, index) => (
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
    );};


    return (
    <><div className="container">
        <div>
          <h1>Student List</h1>

          <form id="search-form" onSubmit={searchStudent}>
              <label htmlFor="studentId">Subject ID:</label>
              <input 
                type="text" 
                name="subjectId" 
                onChange={(e) => setSubjectId(e.target.value)} 
                placeholder="Subject ID" 
                required 
              />
              <button type="submit">Search</button>
          </form>
        </div>
        
        <div className="button-container">
          <button onClick={() => navigate('/')}>Back</button>
        </div>
      </div>
        
        <div>
          {loading && <p>Loading...</p>}
                {searchPerformed && !loading && students.length === 0 && <h2>No Result.</h2>}
                {students.length > 0 && (
                  <div className="table-container">
                    <h2>Students in Subject ID {subjectId}: {students.length} results</h2>
                    {showStudentInfo(students)}
                  </div>
                )}
        </div>
    </>
    );
}


export default StudentList;