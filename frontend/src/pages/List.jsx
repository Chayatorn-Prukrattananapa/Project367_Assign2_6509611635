import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function List () {
    const [course, setCourse] = useState([]);
    const [students, setStudents] = useState({});
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

    useEffect(() => {
        const fetchData = async () => {
          try {
            const response = await fetch("http://localhost:2025/api/subject", {
              method: "GET",
            });
    
            if (!response.ok) throw new Error("Response not ok");
    
            const coursesData = await response.json();
            setCourse(coursesData);
    
            // Fetch students for each course
            const newStudentsMap = {};
            for (const c of coursesData) {
              try {
                const studentRes = await fetch(
                  `http://localhost:2025/api/subject/list/${c.subjectId}`,
                  { method: "GET" }
                );
                if (!studentRes.ok) throw new Error("Student fetch failed");
    
                const studentData = await studentRes.json();
                newStudentsMap[c.subjectId] = studentData;
              } catch (err) {
                console.error(`Error fetching students for ${c.subjectId}:`, err);
                newStudentsMap[c.subjectId] = [];
              }
            }
            setStudents(newStudentsMap);
          } catch (err) {
            console.error("Can't fetch courses:", err);
          }
        };
    
        fetchData();
      }, []);

        const listAll = ({ course, students }) => {
          return course.map((courseItem) => (
            <div className="table-container" key={courseItem.subjectId}>
              <div className="course-section">
                <h2>{courseItem.subjectName}: {courseItem.subjectId}</h2>
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
                    {(students[courseItem.subjectId] || []).map((student, index) => (
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
              </div>
            </div>
          ));
        };

      return (
        <div className="container">
            <div>


            <h1>List All Courses and Students</h1>
            {listAll({course, students})}
            <div className="button-container">
                <button onClick={() => navigate('/')}>Back</button>
            </div>
            <div style={{ height: "2rem" }}></div>
          </div>
        </div>
      );
}
export default List;