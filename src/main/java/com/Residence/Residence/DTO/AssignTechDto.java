package com.Residence.Residence.DTO;

public class AssignTechDto {

    private Long TechId;
    private Long requeteid;

    public Long getRequeteid() {
        return requeteid;
    }

    public void setRequeteid(Long requeteid) {
        this.requeteid = requeteid;
    }

    public Long getTechId() {
        return TechId;
    }

    public void setTechId(Long techId) {
        TechId = techId;
    }
}
