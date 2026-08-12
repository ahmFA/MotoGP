package api.ahm.motogp.championship.domain.model.valueobjects;

public record EventId(Long id) {
    public EventId {
            if(id==null || id <= 0){
                throw new IllegalArgumentException("Event ID must be greater than zero.");
            }
    }
}
