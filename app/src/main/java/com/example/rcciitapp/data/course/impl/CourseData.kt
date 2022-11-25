package com.example.rcciitapp.data.course.impl

import com.example.rcciitapp.R
import com.example.rcciitapp.model.Course
import com.example.rcciitapp.model.Faculty

val cseFaculty = listOf(
    Faculty(
        id = "CSE_1",
        name = "Dr. Minakshi Banerjee",
        degree = "PhD (Engg.)",
        doj = "01.09.2008",
        designation = "Professor",
        email = "minakshi.banerjee@rcciit.org.in",
        image = R.drawable.cse_minakshi
    ),
    Faculty(
        id = "CSE_2",
        name = "Mr. Harinandan Tunga",
        degree = "M.Tech (CSE)",
        doj = "10.06.2004",
        designation = "Associate Professor",
        email = "harinandan.tunga@rcciit.org.in",
        image = R.drawable.cse_harinandan
    )
)
val course1 =
    Course(
        id = "1",
        courseName = "Computer Science & Engineering (CSE)",
        branch = "B.Tech",
        duration = "4 Years ",
        intake = "120",
        faculty = cseFaculty
    )

val course2 =
    Course(
        id = "2",
        courseName = "Electronics & Communication Engineering (ECE)",
        branch = "B.Tech",
        duration = "4 Years ",
        intake = "120"
    )
val course3 =
    Course(
        id = "3",
        courseName = "Information Technology (IT)",
        branch = "B.Tech",
        duration = "4 Years ",
        intake = "120"
    )
val course4 =
    Course(
        id = "4",
        courseName = "Electrical Engineering (EE)",
        branch = "B.Tech",
        duration = "4 Years ",
        intake = "60"
    )
val course5 =
    Course(
        id = "5",
        courseName = "APPLIED ELECTRONICS & INSTRUMENTATION Engineering (AEIE)",
        branch = "B.Tech",
        duration = "4 Years ",
        intake = "30"
    )
val course6 =
    Course(
        id = "6",
        courseName = "Computer Science & Engineering",
        branch = "M.Tech",
        duration = "2 Years ",
        intake = "18"
    )
val course7 =
    Course(
        id = "7",
        courseName = "Computer Science & Engineering (Artificial intelligence)",
        branch = "M.Tech",
        duration = "2 Years ",
        intake = "18"
    )
val course8 =
    Course(
        id = "8",
        courseName = "VLSI and Microelectronics",
        branch = "M.Tech",
        duration = "2 Years ",
        intake = "12"
    )

val course9 =
    Course(
        id = "9",
        courseName = "Master in Computer Application (MCA)",
        branch = "MCA",
        duration = "2 Years ",
        intake = "60"
    )

val courses: List<Course> =
    listOf(course1, course2, course3, course4, course5, course6, course7, course8, course9)