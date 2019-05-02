package github.hemathajay.com.picker;

public class Country {
    private final String iso;
    private final String phoneCode;
    private final String name;

    public Country(String iso, String phoneCode, String name) {
        this.iso = iso;
        this.phoneCode = phoneCode;
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public String getName() {
        return name;
    }

    boolean isEligibleForQuery(String query) {
        query = query.toLowerCase();
        return getName().toLowerCase().contains(query)
                || getIso().toLowerCase().contains(query)
                || getPhoneCode().toLowerCase().contains(query);
    }
}
