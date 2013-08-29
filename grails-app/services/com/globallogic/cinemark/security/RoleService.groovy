package com.globallogic.cinemark.security

class RoleService {

    def findByAuthority(authority) {
        Role.findByAuthority(authority)
    }
}
