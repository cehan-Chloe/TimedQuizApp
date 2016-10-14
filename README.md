# TimedQuizApp

Design and implement a multiple-choice, timed quiz app for smartphones and tablet computers running the Android operating system. The app has two kinds of users: quiz master (QM) and quiz taker (QT).

The app enables the QM to build a quiz database with no limitation on the number of questions. If there is no disk space to store more entries on the quiz database, the app flags an error message. In addition, the QM builds a QT database with a pair of ID and password. The QM labels a question with the number of seconds within which it must be answered. The minimum time given to a question is 10 seconds and the maximum time is 30 seconds. The app gives 5 questions in each round of user login, with an option to continue another round, and another round and so on.

If a question is incorrectly answered or not answered within the given time, the app gives an indication to the user by flashing the correct answer. For an incorrect answer, it is shown in red. For each question, the remaining time is displayed in seconds. If a question is correctly answered, the app takes the user to the next question. On every question screen, the app tells the user how many questions have been attempted and how many questions have been correctly answered.

Before the app is closed, it saves the performance of the QT for further processing and display, such as how many times the QT took quizzes, and her performance in each round.

While giving a round of quiz, the app randomly selects questions and no question is repeated. The QM needs to have the ability to delete the statistics saved for individual QTs. A sample session has been shown below. Make suitable assumptions, if there is a need.
