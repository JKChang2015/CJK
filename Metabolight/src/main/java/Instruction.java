/**
 * Created by jkchang
 * 24/07/2018
 * Tag:
 * Description: parsing the Configuration file
 */

public class Instruction {

    private String uriString;
    private Scope scope;
    private String comment;
    private String newSuperClass;
    public Instruction(String uriString, Scope scope, String comment) {
        this.uriString = uriString;
        this.scope = scope;
        this.comment = comment;
    }

    public String getUriString() {
        return uriString;
    }

    public Scope getScope() {
        return scope;
    }

    public String getComment() {
        return comment;
    }

    public String getNewSuperClass() {
        return newSuperClass;
    }

    public void setNewSuperClass(String newSuperClass) {
        this.newSuperClass = newSuperClass;
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();

        // scope
        if (scope == Scope.SINGLE) {
            buffer.append("S");
        } else if (scope == Scope.UP) {
            buffer.append("U");
        } else if (scope == Scope.DOWN) {
            buffer.append("D");
        }

        // superclass
        if (newSuperClass != null) {
            buffer.append('(').append(newSuperClass).append(')');
        }
        buffer.append(':').append(this.uriString);

        // comment
        if (comment != null) {
            buffer.append(' ').append(comment);
        }
        return buffer.toString();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Instruction)) return false;
        return this.hashCode() == obj.hashCode();
    }

    public enum Scope {
        UP,
        SINGLE,
        DOWN
    }


}


