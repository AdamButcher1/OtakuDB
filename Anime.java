/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adam
 */
public class Anime {
    private String AnimeName;
    private String Director;
    private String Studio;
    private String Writer;
    private String StartDate;
    private String EndDate;
    private int Episodes;
    private String RelatedManga;
    
    public Anime(String AnimeName, String Director, String Studio, String Writer,
            String StartDate, String EndDate, int Episodes, String RelatedManga) {
        this.AnimeName = AnimeName;
        this.Director = Director;
        this.Studio = Studio;
        this.Writer = Writer;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.Episodes = Episodes;
        this.RelatedManga = RelatedManga;
    }
    
    public String getAnimeName() {
        return AnimeName;
    }
    
    public String getDirector() {
        return Director;
    }
    
    public String getStudio() {
        return Studio;
    }
            
    public String getWriter() {
        return Writer;
    }
    
    public String getStartDate() {
        return StartDate;
    }
                    
    public String getEndDate() {
        return EndDate;
    }
    
    public int getEpisodes() {
        return Episodes;
    }
                        
    public String getRelatedManga() {
        return RelatedManga;
    }
    
}
