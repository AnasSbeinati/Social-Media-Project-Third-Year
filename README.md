Cairo University	                  Faculty of Computers and Information
       


CS352 – Software Engineering II
Project Specifications
2015
Version 2.0
Project Team
Staff: Dr Mohammad El-Ramly		m.elramly@fci-cu.edu.eg
TAs:	Eng Mohamed Samir			m.samir@fci-cu.edu.eg Eng Yomna Magdy Mohamed 	yomna@fci-cu.edu.eg Eng Desoky Abd El-qawy		d.abdelqawy@fci-cu.edu.eg Eng Omar Khaled Ali Ragab	o.khaled@fci-cu.edu.eg Eng Ahmed Mohamed Sayed	a.mohamed@fci-cu.edu.eg Eng Ayat Khairy				ayat.khairy@fci-cu.edu.eg Eng Catherine Farag			c.bedrossian@fci-cu.edu.eg 

Introduction
•	This document states the project objectives
•	It also describes its three different project phases 
•	This is an ongoing document that will keep growing with more details
Project Objectives
•	Working in real life project.
•	Gaining experience in cloud computing models.  We will use Google App Engine platform cloud (Paas model).
•	Applying software engineering concepts. (e.g., process, agile, Scum, SCM, etc.)
•	Learning new design concepts and design patterns.
•	Learning about QA activities to ensure code quality.
Project Logistics 
Read these instructions very carefully إقرأ هذه التعليمات بعناية بالغة من أجل نجاح مشروعك
1-	Students from the same lab will be divided into groups; each group is 4 members 
2-	Project weight is 15 marks from the total course mark.
3-	We will follow Agile software developing cycle (Scrum)
4-	In Scrum there are 3 main roles. Product owner, scrum master and the team
5-	Product owner will be your lab TA 
6-	Chief product owner whom you can also ask is TA Mohamed Samir
7-	One member from each group will be the scrum master
8-	The Scrum Master serves as a facilitator for both the Product Owner and the team. 
9-	The Scrum Master will work with product owner to make sure the product backlog is in a good shape
Meetings between scrum master and product owner will be periodically (daily, weekly)
10-	Each team will work in sprints, they will have a backlog requirements in each sprint
11-	We will use Google App Engine as a cloud model and JAX-RS to build Restful services
12-	For frontend, each team will decide to use any environment, your frontend may be "Web" or "Android Mobile". You should follow MVC structure. For web, you may use Jersey technology for building MVC Web Applications. 
13-	See the staff members table above to know TAs 
14-	Academic honesty is assumed. All work submitted must be original and written by your team (Not copied from students, the net, outside sources). Plagiarism will be penalized.
•	Soon, you will be our colleague and we will be proud of you. 
•	Professional conduct and practice is essential in your career.
•	الممارسات المهنية و الأخلاقية السليمة طريقك أولا لمرضاة الله و ثانيا للنجاح و التميز فى المهنة 
•	بالغش تخدع نفسك قبل أن تخدع غيرك
Project Phases:  
Phase	Deliverables	Deadline	Mark
Phase 1-a	Understand code and requirements, create Github workspace	28 February	1
Phase 1-b	Use an agile planning board for creating and managing user stories (tasks), implement additional functionality, Create services for these functionalities.	7 March	2
Phase 1-c	Deploy on the social network engine on Google App Engine	14 March	2
Phase 2-a	Extend System with new requirements and applying design patterns	26 March	2.5
Phase 2-b	Extend System with new requirements and applying design patterns	18 April	4.5 (+ 1 Bonus)
Phase 3-a	Code Review	1 May 	2
Phase 3-b	Code Testing	9 May	2 (+ 0.5 bonus)
Phase 3-c	Code Refactoring	15 May	2
Project Description
	Social media sites have changed the way we interact with each other. Sites like Facebook, Twitter, LinkedIn, Vine and more make it simple to stay connected in people’s lives. You can easily catch up with friends with their statuses, photos and videos they post. And social media is not just important for your personal life, it is also a great tool for businesses or anyone trying to develop their professional network or look for a job.
In this project we will build our own version of social network
Main Components
Users
With this component you should be able to create and manage different type of users (for example: normal user, premium user which has advanced features, … ) and maintain some important attributes for each user (e.g. name, email, gender, profile picture, … )
Groups
With this component you should be able to create and manage different types of groups (for example: public group, private groups, … ) each group is managed by set of users (including the user who created the group).
Pages
Users may need to create a fan page for specific entity or product. It's the same idea as Facebook pages. With this component you should be able to create and manage user pages and maintain some important attributes (e.g. number of users who like a specific page)
Posts
Users may want to share their status in social network by writing posts, Posts is one of the building blocks of social media content. A post (in our social network) is defined as text content written by a specific user. Any other friend user can "like" this text content or share it on his/her personal page. A user can write post in his/her personal page or in a joined group. If a user owns a page, he can write a post in his/her page.
With this component you should be able to create and manage different types of posts (user post, page post, post written by premium users, etc.). Also you should maintain some important attributes for a post (number of likes, number of shares)
Hashtag
Users use hashtags to categorize the content of specific post. If a user wants to write a post about "education", he may put the word #education (# the symbol of hashtag) in the post to categorize this post as education post. If another user wants to read some posts about education, so user may request all posts containing #education phrase.
With this component you should be able to manage all hashtags, get all posts containing specific hashtag and sort these posts according to post importance (for examples, posts written by sponsored users should be more important than posts written by normal users)
Messages
User can send message to another user(s) in his/her friends list.
Using this component, you should be able to create message from user to any other user(s), or get messages between 2 users, or get messages in specific message group (which contains more than 2 users)
Notifications
This component will be responsible for notifying users for important activities. Important activities such as replying on post, like a post, received messages, received friend requests, … 
Functional Requirements in phase1
ID	Function name	Description
1	Signup – Create new User	User should create a new account with unique user name and has a password so that he can use the system.

2	Login	User must login to interact with the system. User will login to the system with username and password stored in system

3	Signout	User can sign out from the system anytime and leave the system in current time
4	Send Friend Request	User can send a friend request to another user in the system, this request will be pending request until the anther user accepts this request
5	Add Friend	User can accept any of the pending requests he receives, user can receive friend requests from the other users and if user accept a request then becomes a friend with the sender of this request

Existing system (code) achieves the first three requirements. You should extend the existing system to add requirements 3,4 and 5. See link for source code in references.
Functional Requirements in phase2-a
ID	Function name	Description
1	Messages	Any user can send message to any friend in social media
2	Send group messages (conversation)	Any user can create conversation chat contains some users. Each user should be notified if there's new message in conversation.
In order to implement this requirement efficiently, you may use observer design pattern. 
3	Notifications & Notification history	Any important action to current active user should be recorded and current active user should be notified.
Your system should store all notifications for current active user, each notification may has specific reaction (callback).
In order to implement this requirement efficiently, you may use command design pattern 
Functional Requirements in phase 2-b
ID	Function name	Description
1	Create Post	Any user can write post on any timeline(we will address timeline requirements later). User can write post on his/her timeline or on any friend timeline. Each post should has owner and content and number of likes (Hint: You may use builder design patter)
2	Page post	When user write post on his/her page (we will address this requirement later) this user should see "number of seen" for this post, number of users see this post.
3	User post	When user write post on his/her timeline this user can add "Feelings" to this post (the same meaning as facebook)
4	Share post	Any user can share any post on his/her wall (Share concept is similar to share concept in Facebook)
5	Post privacy	Each post should has privacy (regardless post type), post privacy is "public","private" or "custom". Public posts is visible to everyone in social network. Private posts is just visible to friends of author user who write this post, if this post is page post and has private privacy then this post will be visible to users like this page. Custom privacy means that author of this post will select who exactly can see this post (specific users, specific types of users)  
(Hint: to include privacy in post component you may use bridge design pattern)
6	Hashtag statistics	Any user can view number of posts contains specific hashtag, and can see timeline of this hashtag
7	Hashtag trends	In any timeline, trends section should exist, this section contains the most 10 hashtags contains number of posts
8	Pages	Any user can create page, page attributes is name, type, category, number of likes and reach (number of active users with page). Page owner should see number of seen for each post in the page
9	Like Page	Any user can like any page to be able to see some page's posts on his/her timeline
10	Timeline	All users, hashtags or pages, should have timeline which contains posts in this thread (user posts, hashtag posts or pages posts)
Example of the current software Usage
Current system is web application uses Rest web services to make core functions, web application follow MVC architecture, also it uses google data stores to save data.
 
Login:
User should be able to login to the system; current system uses JAX-RS to make REST web services. In frontend, firstly users enters data then data redirects to goHome function in service class and this function will call login web service to check data and returns user data
LoginService: http://fci-swe-apps.appspot.com/rest/LoginService
Parmaters: uname, email
Type: POST
Content-type: x-www-urlencoded
SignUp:
User should be able to register in the system,  current system uses JAX-RS to make REST web services. In frontend, firstly users enters data then data redirects to response function in service class and this function will call registration web service to insert data and returns true if data is inserted in data store correctly
LoginService: http://fci-swe-apps.appspot.com/rest/RegistrationService
Parmaters: uname, email, password
Type: POST
Content-type: x-www-urlencoded
Phase 1
•	Given some requirements, design and implementation for some requirements, understand these requirements and current existing system, you should extend the implementation and design to achieve new requirements.

•	Notes:
o	Your role is to understand the main features and requirements of the product and current existing system.
o	Talk with TA if there's anything not clear in requirements or in current existing system.
Details
•	In phase1-a, each team will read and understand project description, then read and understand current requirements and design
•	Read the current existing code for this project very well
•	Create Github workspace for this project.
•	In phase 1-a your team will deliver github workspace and frontend environment (your frontend will be web or mobile i.e. Android) 
•	Read phase1-a template very well, you will submit this template 
•	In phase1-b, use Trello (or Assembla) software to plan for agile processing
•	Tasks will be assigned by scrum master to team members using Trello (or Assembla)
•	Based on your understanding of current system, extend this implementation and design to add new requirements
•	Don't create new project to add these projects, just extend this project implementation. YOU MUST use the given implementation.
•	Your code should be documented and follow standard Java coding style
•	In phase 1-b you should deliver new implementation, and list of current Rest services in your system¸ also you will deliver meeting reports between team members and scrum master
•	Read phase1-b template very well, you will submit this template
•	Also you should fill "Daily Scrum Meeting Template", so read this template very well, you will submit this template with phase1-b template 
•	In phase 1-c, you should create new project on google app engine deploy the new project on Google App Engine.
•	In phase 1-c, you should deliver working system (web application, android apk or any frontend running environment) and list of implemented services deployed on google app engine

•	Read phase1-c template very well, you will submit this template with documentation for each meeting using "Daily scrum meeting template"
Phase 2
Details
•	In phase2, each team will read and understand project description and current requirements, then make design and implementation for these requirements
•	In phase2-a, you have specific requirements  and you should implement this requirements 
•	Use the same github workspace you have already created in phase1.
•	Update your trello project and add new tasks for each new requirement
•	You should deliver  new class diagram design (after applying these requirements) 
•	Don't create new project to add these projects, just extend your project implementation. YOU MUST use the given implementation.
•	New functionalities will be properly wrapped as services deployed on the cloud
•	You should fill phase2-a template and submit it in acadox, So read this template very well
•	In phase2-b, you have new requirements you should be able to implement this requirements
•	Extend your current design to take into consider these new requirements in phase2-b
•	Expect some change in requirements during designing and implementation phase
•	You should fill phase2-b template and submit it in acadox, So read this template very well
•	Your code should be documented and follow standard Java coding style
•	You should deliver new implementation, and list of current Rest services in your system.
 
Phase 3
•	The target of this phase is to review and test implementation to find any errors or defects. Then we will refactor the implementation to enhance the quality of the design and code. 
•	In Phase 3-a each team will be assigned the task of helping another team improve his implementation.  This will be done be review the implementation for some random team and creating GitHub issues for them on all the problems found in their code.
•	It's required from each team to:
1-	Create an issue for each problem in the other team’s Github repository.
2-	Prepare document containing list of problems in the implementation of the other teams.
•	We will provide a template for the report including a sample checklist to use in the review process.
•	Use the checklist as a starting point. You should optimize it for your specific needs as issues arise in the code review process.
•	Another useful checklist is available here:
http://blog.fogcreek.com/increase-defect-detection-with-our-code-review-checklist-example/
•	Check the attached list to know which team you should review. You are given their repository link on GitHub to write issues for them.
•	Send them the finished report and upload it in acadox.
•	In Phase3-b each team will make unit testing for backend components (entities) and frontend (i.e. calling rest services). The purpose of unit testing is ensuring that each function and each component individually works well alone.
•	Also it's required from each team to make integration testing between backend entities and frontend components. The purpose of integration testing is ensuring that different components work well together in achieving a common scenario.
•	It's required from each team to:
1-	Create testing package for each entity (or class) in backend component (or server side component)
2-	If there's a function in entity depends on another function so you should make dependence between these 2 function (integration testing on backend  component)
3-	In frontend testing component you should call some service and check result of calling this service
4-	Your frontend testing component should depend on backend testing component
5-	You should develop enough test cases to cover all different important cases including valid and invalid inputs.
6-	We will automate these test cases execution by writing them using TestNG framework and writing a regression testing program to run all the test cases.

Tools Used in The Project 
GitHub
•	Every team member must use bit Github account. Any careless behavior won't be accepted (e.g one team member upload data)
•	It will be used for documents and code.
•	Github history much show real utilization for the project. Any trial to work away of it and upload files in last moments won't be accepted.
Google App Engine and JAX-RS API
•	All team members must learn this technology and TA may ask them about it.
Trello (or Assembla)
•	You must organize all your tasks (user stories and deployment tasks and all other tasks in one of these tools and show evidences in using the tool for management.
Grading
Phase 1:
	Phase1-a: 
Github workpace (0.5 grade)
You should develop and submit your frontend environment to this project
Choose frontend (0.5 grade)
	Phase1-b:
		Doing functionalities (1.5 grade)
			Send friend request service (0.5 grade)
			Add friend service (0.5 grade)
			Store data in google datastore (0.25 grade)
			Coding style (0.25 grade)
		Using Trello for scrum, put requirements backlog (0.25 grade)
		Meeting reports between scrum master and team members (0.25 grade)	
	Phase 1-c:
		Create Google App Engine project 			(0.25 grade)
		Create Web or Android frontend 			(0.25 grade)
Deploy this project on Google App Engine 		(1 grade)
		List of implemented services 				(0.5 grade)
		-0.25 for not providing daily scrum meeting
		-0.25 for not providing javadoc for each class
Phase 2: 
	Phase2-a: 
Correct class diagram design and applying SOLID principles 
(Provided that the functionalities working correctly)	(1 grade)
Implementing message (1 to 1 and conversation) requirements (0.5grade)
Implementing notifications requirement 			(0.5 grade) 
Github and Trello 						(0.5 grade)

	Phase2-b:
Correct class diagram (Provided that the functionalities working correctly)									(0.75 grade)
		Post creation							(0.75 grade)
		Post privacy							(0.5 grade)	
                 (+0.5 Bonus for handling posts with multiple privacy options)
		Save hashtag data						(0.5 grade)
		Page & like page						(0.5 grade)
		Timeline							(0.75 grade)
Share post   (+0.5 Bonus for handling this requirement correctly)
Github and Trello 						(0.75 grade)
Phase 3: 
	Phase3-a: 
Issues in GitHub							1 grade
1-	Number of issues you made and Variation of issues (covering code, design, style, comments, etc.)					0.25
2-	Details and depth of the discussion of each issue. (Explaining what it is and where it is in the code, etc.)					0.25
3-	Usefulness of the comments you wrote for the other team.	0.25
4-	Suggestions for enhancing and improving the design and code.	0.25
Review report 								1 grade
5-	The application of the checklist and discussion why this code adheres (or not) to each item on the list.					0.25
6-	Quality, number and depth of problems you found.		0.25
(These problems are reported in details as issues in GitHub)	
7-	Evaluation if SOLID design principles apply or not.		0.25
8-	Suggestions for improvement or use of design patterns.	0.25
Phase 3-b:
	Backend Testing (1 Grade)
1-	Make testing class (USING TESTNG) for each entity in backend (0.25 grade)
2-	Create testing functions for each function in each entity (0.5 grade)
3-	Handle dependency between functions in each entity (0.25 grade)
Frontend Testing (1 Grade)
1-	Make testing class (USING TESTNG) for each component in frontend 
(0.25 grade)
2-	Create testing functions for each function in each component (0.5 grade)
3-	Handle dependency between functions in each entity (0.25 grade)

Make Frontend testing package depend on backend testing package (0.5 Grade)

References
http://docs.oracle.com/javaee/6/tutorial/doc/giepu.html
http://www.vogella.com/tutorials/REST/article.html
https://jersey.java.net/documentation/latest/mvc.html
http://www.vogella.com/tutorials/GoogleAppEngineJava/article.html
https://cloud.google.com/appengine/docs/java/datastore/
https://trello.com/
https://www.assembla.com/home
Source Code Repository: https://github.com/mohamedsamir92/FCI-SWEII-SocialNetwork
Source Code(Android frontend) Repository: https://github.com/mohamedsamir92/FCI-SWEII-SocialNetwork-Android
Source code review checklist http://blog.fogcreek.com/increase-defect-detection-with-our-code-review-checklist-example/ 
Policy Regarding Plagiarism:  
	
Students have collective ownership and responsibility of their project. Any violation of academic honesty will have severe consequences and punishment for ALL team members.
1.	تشجع الكلية على مناقشة الأفكار و تبادل المعلومات و مناقشات الطلاب حيث يعتبر هذا جوهريا لعملية تعليمية سليمة
2.	ساعد زملاءك على قدر ما تستطيع و حل لهم مشاكلهم فى الكود و لكن تبادل الحلول غير مقبول و يعتبر غشا.
3.	أى حل يتشابه مع أى حل آخر بدرجة تقطع بأنهما منقولان من نفس المصدر سيعتبر أن صاحبيهما قد قاما بالغش.
4.	قد توجد على النت برامج مشابهة لما نكتبه هنا أى نسخ من على النت يعتبر غشا يحاسب عليه صاحبه.
5.	إذا لم تكن متأكدا أن فعلا ما يعد غشا فلتسأل المعيد أو أستاذ المادة.
6.	فى حالة ثبوت الغش سيأخذ الطالب سالب درجة المسألة ، و فى حالة تكرار الغش سيرسب الطالب فى المقرر.

