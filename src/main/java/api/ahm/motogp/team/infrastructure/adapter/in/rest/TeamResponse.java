package api.ahm.motogp.team.infrastructure.adapter.in.rest;

public record TeamResponse(
        Integer id,
        String name,
        Boolean active
) {
}
