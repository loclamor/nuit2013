package nuitinfo2013

class ConnectionHistory {

    Date connectionDate = new Date()

    static belongsTo = [user : User]
    static constraints = {
        connectionDate nullable: false, blank: false
    }
}
