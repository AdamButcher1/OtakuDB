CREATE DATABASE OtakuDB;

CREATE TABLE MANGA( 
	MangaName varchar(100) PRIMARY KEY NOT NULL,
	StartDate date,
	EndDate date,
	NumOfVolumes varchar(4), 
);

CREATE TABLE ANIME (
	AnimeName varChar(100) PRIMARY KEY NOT NULL,
	Director TEXT,
	Studio TEXT,
	Writer TEXT,
	StartDate DATE,
	EndDate DATE,
	Episodes INTEGER,
	RelatedManga varChar(100),
	FOREIGN KEY (RelatedManga) REFERENCES MANGA(MangaName) on UPDATE cascade
);

CREATE TABLE VOICE_ACTOR(
	VoiceActorFname varChar(100) NOT NULL,
	VoiceActorLname varChar(100) NOT NULL,
	VoiceActorSex Char(1) CHECK (VoiceActorSex = 'M' or VoiceActorSex = 'F'),
	VoiceActorBirthday DATE,
	PRIMARY KEY(VoiceActorFname,VoiceActorLname)
);

CREATE TABLE EPISODE(
	AnimeName varChar(100),
	EpisodeNumber INTEGER,
	EpisodeTitle TEXT,
	EpisodeDescription TEXT,
	Airdate DATE,
	PRIMARY KEY(AnimeName,EpisodeNumber),
	FOREIGN KEY (AnimeName) REFERENCES ANIME(AnimeName) on UPDATE cascade
);

CREATE TABLE CHARACTER(
	CharacterName varChar(100) NOT NULL,
	AnimeIn varChar(100) NOT NULL,
	VoiceActorFname varChar(100),
	VoiceActorLname varChar(100),
	PRIMARY KEY(CharacterName,AnimeIn),
	FOREIGN KEY(VoiceActorFname,VoiceActorLname) REFERENCES VOICE_ACTOR(VoiceActorFname,VoiceActorLname),
	FOREIGN KEY(AnimeIn) REFERENCES ANIME(AnimeName) on UPDATE cascade
);