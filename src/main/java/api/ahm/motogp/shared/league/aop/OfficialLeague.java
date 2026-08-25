package api.ahm.motogp.shared.league.aop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

public class OfficialLeague {

    private final static long officialLeagueId = 1L;

    public static long getOfficialLeagueId() {
        return officialLeagueId;
    }
}
