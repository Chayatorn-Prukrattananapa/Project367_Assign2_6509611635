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
        const response = await fetch(`http://localhost:2025/api/subject/${subjectId}/student-list`);
        if (!response.ok) throw new Error("Network response was not ok");
    
        const data = await response.json();
        setStudents(data);
      } catch (error) {
        console.error("Error fetching students:", error);
        setStudents([]);
      } finally {
        setLoading(false);
      }
    };

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
                {searchPerformed && !loading && students.length === 0 && <p>No students found.</p>}

                {students.length > 0 && (
                  <ol>
                    {students.map((student, index) => (
                      <li key={index}>
                        {student.name} ({student.studentId})
                      </li>
                    ))}
                  </ol>
                )}
        </div>
    </>
    );
}


export default StudentList;