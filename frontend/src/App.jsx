import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import StudentList from './pages/StudentList'; 
import DeleteCourse from './pages/DeleteCourse';
import List from './pages/List'

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/student-list" element={<StudentList />} />
        <Route path="/delete-course" element={<DeleteCourse />} />
        <Route path="/list-all" element={<List />} />
      </Routes>
    </Router>
  );
}

export default App;