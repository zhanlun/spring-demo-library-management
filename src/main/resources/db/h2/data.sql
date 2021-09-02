INSERT INTO subject (id, name) VALUES (1, 'Fiction');
INSERT INTO subject (id, name) VALUES (2, 'Science');
INSERT INTO subject (id, name) VALUES (3, 'Social Sciences');
INSERT INTO subject (id, name) VALUES (4, 'Engineering');
INSERT INTO subject (id, name) VALUES (5, 'Computing');
INSERT INTO subject (id, name) VALUES (6, 'Miscellaneous');


INSERT INTO book (id, name, subject_id, publisher, published_year, contributors, summary, cover_image_path, number_of_copy)
VALUES (1, 'The Midnight Library', 1, 'Viking', 2020, 'Matt Haig',
'Between life and death there is a library, and within that library, the shelves go on forever. Every book provides a chance to try another life you could have lived. To see how things would be if you had made other choices . . . Would you have done anything different, if you had the chance to undo your regrets?',
'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1602190253l/52578297.jpg', 2);

INSERT INTO book (id, name, subject_id, publisher, published_year, contributors, summary, cover_image_path, number_of_copy)
VALUES (2, 'The Moonlight Child', 1, 'NIGHTSKY PRESS', 2020, 'Karen McQuestion',
'On a cold January night, Sharon Lemke heads outside to see a lunar eclipse when she notices something odd at the house behind her backyard. Through her neighbor''s kitchen window, she sees what appears to be a little girl washing dishes late at night. But the Fleming family doesn''t have a child that age, and even if they did, why would she be doing housework at this late hour?',
'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1595946755l/54733883._SY475_.jpg', 2);

INSERT INTO book (id, name, subject_id, publisher, published_year, contributors, summary, cover_image_path, number_of_copy)
VALUES (3, 'Mathematical Methods for Physics and Engineering : A Comprehensive Guide', 2, 'Cambridge University Press', 2006, 'K. F. Riley;M. P. Hobson;S. J. Bence',
'The third edition of this highly acclaimed undergraduate textbook is suitable for teaching all the mathematics for an undergraduate course in any of the physical sciences. As well as lucid descriptions of all the topics and many worked examples, it contains over 800 exercises. New stand-alone chapters give a systematic account of the ''special functions'' of physical science, cover an extended range of practical applications of complex variables, and give an introduction to quantum operators. Further tabulations, of relevance in statistics and numerical integration, have been added. In this edition, half of the exercises are provided with hints and answers and, in a separate manual available to both students and their teachers, complete worked solutions. The remaining exercises have no hints, answers or worked solutions and can be used for unaided homework; full solutions are available to instructors on a password-protected web site, www.cambridge.org/9780521679718.',
'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/5216/9780521679718.jpg', 3);

INSERT INTO book (id, name, subject_id, publisher, published_year, contributors, summary, cover_image_path, number_of_copy)
VALUES (4, 'Fundamentals of Physics Extended', 2, 'John Wiley & Sons Inc', 2013, 'David Halliday;Robert Resnick;Jearl Walker',
'This new edition offers most accurate, extensive and varied set of assessment questions of any course management program in addition to all questions including some form of question assistance including answer specific feedback to facilitate success. The text also offers multimedia presentations (videos and animations) of much of the material that provide an alternative pathway through the material for those who struggle with reading scientific exposition. Furthermore, the book includes math review content in both a self-study module for more in-depth review and also in just-in-time math videos for a quick refresher on a specific topic.',
'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/1182/9781118230725.jpg', 3);

INSERT INTO book (id, name, subject_id, publisher, published_year, contributors, summary, cover_image_path, number_of_copy)
VALUES (5, 'Introduction to Psychology', 3, 'Cengage Learning, Inc', 2016, 'James Kalat',
'James Kalat''s best-selling INTRODUCTION TO PSYCHOLOGY does far more than cover major theories and studies; it teaches you how to become better at evaluating information. Hands-on "Try It Yourself" activities and summaries of real research encourage you to ask yourself, "How was this conclusion reached?" and "Does the evidence really support it?" Students praise this streamlined, visually appealing text, which invites you to interact with psychological ideas and expands your preconceived ideas about the field of psychology. As a result, you''ll become a savvier consumer of information, not only during your college experience but also as you venture into your post-college life. With his friendly writing style and many learning tools, Kalat puts you at ease and enables you to participate actively in what you are studying.',
'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/3052/9781305271555.jpg', 1);

INSERT INTO book (id, name, subject_id, publisher, published_year, contributors, summary, cover_image_path, number_of_copy)
VALUES (6, 'Foundations of Analog and Digital Electronic Circuits', 4, 'Elsevier Science & Technology', 2011, 'Anant Agarwal;Jeffrey Lang',
'Unlike books currently on the market, this book attempts to satisfy two goals: combine circuits and electronics into a single, unified treatment, and establish a strong connection with the contemporary world of digital systems. It will introduce a new way of looking not only at the treatment of circuits, but also at the treatment of introductory coursework in engineering in general. Using the concept of ''abstraction,'' the book attempts to form a bridge between the world of physics and the world of large computer systems. In particular, it attempts to unify electrical engineering and computer science as the art of creating and exploiting successive abstractions to manage the complexity of building useful electrical systems. Computer systems are simply one type of electrical systems.',
'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/5586/9781558607354.jpg', 2);

INSERT INTO book (id, name, subject_id, publisher, published_year, contributors, summary, cover_image_path, number_of_copy)
VALUES (7, 'The Algorithm Design Manual', 5, 'Springer Nature Switzerland AG', 2020, 'Steven S. Skiena',
'This newly expanded and updated third edition of the best-selling classic continues to take the "mystery" out of designing algorithms, and analyzing their efficiency. It serves as the primary textbook of choice for algorithm design courses and interview self-study, while maintaining its status as the premier practical reference guide to algorithms for programmers, researchers, and students.',
'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9783/0305/9783030542559.jpg', 2);

INSERT INTO book (id, name, subject_id, publisher, published_year, contributors, summary, cover_image_path, number_of_copy)
VALUES (8, 'Academic Writing : A Handbook for International Students', 6, 'Taylor & Francis Ltd', 2018, 'Stephen Bailey',
'Now in its fifth edition, Academic Writing helps international students succeed in writing essays and reports for their English-language academic courses. Thoroughly revised and updated, it is designed to let teachers and students easily find the topics they need, both in the classroom and for self-study.',
'https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9781/1380/9781138048744.jpg', 1);


INSERT INTO visitor (id, first_name, last_name)
VALUES (1, 'Leanne', 'Graham');
INSERT INTO visitor (id, first_name, last_name)
VALUES (2, 'Ervin', 'Howell');
INSERT INTO visitor (id, first_name, last_name)
VALUES (3, 'Clementine', 'Bauch');
INSERT INTO visitor (id, first_name, last_name)
VALUES (4, 'Patricia', 'Lebsack');

INSERT INTO checkout (id, book_id, visitor_id, checkout_date, due_date, return_date)
VALUES (1, 1, 1, '2021-08-31 11:00:00', '2021-10-01 11:00:00', null);

INSERT INTO checkout (id, book_id, visitor_id, checkout_date, due_date, return_date)
VALUES (2, 3, 1, '2021-07-21 11:00:00', '2021-08-21 11:00:00', '2021-08-18 15:00:00');

