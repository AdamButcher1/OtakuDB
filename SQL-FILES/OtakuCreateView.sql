--Creates view by joining the characters along with the voice actors, and gives information about the voice actor sex
CREATE VIEW SexOfCharacterVoiceActors AS
SELECT C.CharacterName, V.VoiceActorFname, V.VoiceActorLname, V.VoiceActorSex
FROM Character C join VOICE_ACTOR V on C.VoiceActorFname = V.VoiceActorFname and C.VoiceActorLname = V.VoiceActorLname;

--Selection query
Select * FROM [SexOfCharacterVoiceActors];
