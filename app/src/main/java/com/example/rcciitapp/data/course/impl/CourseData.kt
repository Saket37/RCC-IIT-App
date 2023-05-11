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
    ), Faculty(
        id = "CSE_3",
        name = "Dr. Dipankar Majumdar",
        degree = "PhD (Engg.)",
        doj = "02.09.2010",
        designation = "Professor",
        email = "dipankar.majumdar@rcciit.org.in",
        image = R.drawable.cse_dipankar
    ), Faculty(
        id = "CSE_4",
        name = "Mr. Rajib Saha,",
        degree = "M.Tech (CT)",
        doj = "01.03.2007",
        designation = "Assistant Professor",
        email = "rajib.saha@rcciit.org.in",
        image = R.drawable.cse_rajib
    ), Faculty(
        id = "CSE_5",
        name = "Mr. Jaydip Mukhopadhyay",
        degree = "M.Tech (CSA)",
        doj = "01.09.2006",
        designation = "Assistant Professor",
        email = "jaydip.mukhopadhyay@rcciit.org.in",
        image = R.drawable.cse_jaydip
    ), Faculty(
        id = "CSE_6",
        name = "Mr. Koushik Mallick",
        degree = "ME (CSE)",
        doj = "03.08.2009",
        designation = "Assistant Professor",
        email = "koushik.mallick@rcciit.org.in",
        image = R.drawable.cse_koushik
    ), Faculty(
        id = "CSE_7",
        name = "Sk Mazharul Islam",
        degree = "ME (IT)",
        doj = "16.07.2010",
        designation = "Assistant Professor",
        email = "mazharul.islam@rcciit.org.in",
        image = R.drawable.cse_mazharul
    ), Faculty(
        id = "CSE_8",
        name = "Dr. Anup Kumar Kolya",
        degree = "PhD (Engg.)",
        doj = "02.05.2014",
        designation = "Assistant Professor",
        email = "anupkumar.kolya@rcciit.org.in",
        image = R.drawable.cse_anup
    ), Faculty(
        id = "CSE_9",
        name = "Mrs. Monika Singh",
        degree = "M.Tech (CSE)",
        doj = "04.06.2014",
        designation = "Assistant Professor",
        email = "monika.singh@rcciit.org.in",
        image = R.drawable.cse_monika
    ), Faculty(
        id = "CSE_10",
        name = "Mr. Somenath Nag Choudhury",
        degree = "M.Tech (CSE)",
        doj = "01.03.2016",
        designation = "Assistant Professor",
        email = "somenathnag.choudhury@rcciit.org.in",
        image = R.drawable.cse_somenath
    ), Faculty(
        id = "CSE_11",
        name = "Mrs. Alokananda Dey",
        degree = "M.Tech (SE)",
        doj = "09.03.2007",
        designation = "Assistant Professor",
        email = "alokananda.dey@rcciit.org.in",
        image = R.drawable.cse_alokananda
    ), Faculty(
        id = "CSE_12",
        name = "Mrs. Satabdwi Sarkar",
        degree = "M.Tech (CT)",
        doj = "26.02.2007",
        designation = "Assistant Professor",
        email = "satabdwi.sarkar@rcciit.org.in",
        image = R.drawable.cse_satabdwi
    ), Faculty(
        id = "CSE_13",
        name = "Mr. Souvik Majumdar",
        degree = "M.Tech (CSE)",
        doj = "22.02.2019",
        designation = "Assistant Professor",
        email = "souvik.majumdar@rcciit.org.in",
        image = R.drawable.cse_satabdwi
    ), Faculty(
        id = "CSE_14",
        name = "Mr. Anirban Dey",
        degree = "M.Tech (CSE)",
        doj = "25.02.2019",
        designation = "Assistant Professor",
        email = "anirban.dey@rcciit.org.in",
        image = R.drawable.cse_anirban
    ), Faculty(
        id = "CSE_15",
        name = "Mrs. Priya Sen Purkait",
        degree = "M.Tech (CSE)",
        doj = "27.08.2019",
        designation = "Assistant Professor",
        email = "priyasen.purkait@rcciit.org.in",
        image = R.drawable.cse_priya
    ), Faculty(
        id = "CSE_16",
        name = "Mr. Swapan Shakhari",
        degree = "M.Tech (IT)",
        doj = "30.08.2019",
        designation = "Assistant Professor",
        email = "swapan.shakhari@rcciit.org.in",
        image = R.drawable.cse_swapan
    ), Faculty(
        id = "CSE_17",
        name = "Mr. Bishal Ghosh",
        degree = "M.Tech (CSE)",
        doj = "15.06.2022",
        designation = "Assistant Professor",
        email = "bishal.ghosh@rcciit.org.in",
        image = R.drawable.cse_bishal
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
        courseName = "Applied Electronics & Instrumentation Engineering (AEIE)",
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
        courseName = "Computer Science & Engineering (Artificial Intelligence)",
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