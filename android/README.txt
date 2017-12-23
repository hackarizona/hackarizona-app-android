Introduction

	This is an open source application that was developed 
	for Hack Arizona's 2018 event in January. The functionality 
	in the current app includes a map, schedule, social media links,
	a livestream link and a mentor hub. 

Notes for future developers:

	The current build is targeting Nougat devices, and will need to be 
	updated this next year. This should not be an issue for this year's
	event, since 0.3 percent of users have Oreo. The app has also been tested
	on emulators running Oreo, and seems to work fine.  The min SDK is Lollipop,
	which still has about 20% of android users.

	Some suggestions I would make for future developers are to work on 
	making the app more modular, and minimizing the number of times 
	data is being pulled from json on the website, or through a database
	(if you end up implementing a database). Json by its nature is 
	extremely slow, so it is worthwhile to look into alternatives.

	Currently the app uses json publicly available on the Hack AZ website
	to pull schedule information, and push notifications are done through 
	Firebase. Note that Firebase has a default push notification implementation
	for when the app is running in the background, and it is not possible to 
	edit this. However, push notifications can be implemented for
	the app for while it is running in the foreground (this is not implemented
	by default in Firebase, but was implmented in this app).
	
	One suggestion is don't touch the gradle files as much as possible,
	as every edit rebuilds the app and can lead to hours of unnecessary updates
	if you're running on windows. 
	Android Studio offers many tools to update dependencies for you; use those as 
	much as possible. However, anticipate manually editing gradles files 
	from time to time.
	
	
	
	
	
	

