package AlmogAsiaDolfinVarshev;

abstract class User {
    private String username, password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object other) {
        if (!(other instanceof User)) {
            return false;
        }

        if (!(super.equals(other))) {
            return false;
        }

        User temp = (User) other;
        boolean equal = false;
        if (this.username.equals(temp.getUsername())
                && this.password.equals(temp.getPassword())) {
            equal = true;
        }
        return equal;
    }

    public String toString() {
        return "Name: " + username;
    }
}
