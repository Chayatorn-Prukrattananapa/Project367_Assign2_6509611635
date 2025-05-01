import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';         // your current page
import StudentList from './pages/StudentList';   // the page to go to
import DeleteCourse from './pages/DeleteCourse'; // the page to go to

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/student-list" element={<StudentList />} />
        <Route path="/delete-course" element={<DeleteCourse />} />
      </Routes>
    </Router>
  );
}

export default App;