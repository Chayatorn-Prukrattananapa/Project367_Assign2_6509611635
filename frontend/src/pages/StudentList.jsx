import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

function StudentList() {

    const [subjectId, setSubjectId] = useState("");
    const [students, setStudents] = useState([]);
    const [searchPerformed, setSearchPerformed] = useState(false);
    const [loading, setLoading] = useState(false);

    const searchStudent = async (e) => {
        e.preventDefault();
        setLoading(true);
        setSearchPerformed(true);

        try {
            // Replace this with your actual fetch/axios call
            const response = await fetch(`/api/subject/search/${subjectId}`);
            if (!response.ok) throw new Error("Network response was not ok");
      
            const data = await response.json();
            setStudents(data);
          } catch (error) {
            console.error("Error fetching students:", error);
            setStudents([]); // clear list on error
          } finally {
            setLoading(false);
          }
    };

    return (
    <>
        <div>
            <h1>Student List</h1>
        </div>

        <form id="search-form" onSubmit={searchStudent}>
            <label htmlFor="studentId">Subject ID:</label>
            <input type="text" name="subjectId" placeholder="Subject ID" required />
            <button type="submit">Search</button>
        </form>
    </>
    );
}


export default StudentList;