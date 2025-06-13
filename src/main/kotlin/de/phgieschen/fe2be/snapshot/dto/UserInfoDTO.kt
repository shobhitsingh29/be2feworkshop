package de.phgieschen.fe2be.snapshot.dto

import de.phgieschen.fe2be.snapshot.dto.UserType.SUPPLIER
import de.phgieschen.fe2be.snapshot.dto.UserType.ZALANDO_EMPLOYEE

data class UserInfoDTO(
    val email: String,
    val name: String,
    val preferredUsername: String,
    val permissions: List<PermissionDTO>,
    val userType: UserType
) {
    companion object {
        fun supplier(): UserInfoDTO {
            return UserInfoDTO(
                email = "user@example.com",
                name = "User",
                preferredUsername = "User",
                permissions = listOf(
                    PermissionDTO(
                        name = "pqs.pl.read",
                        hasAllSuppliers = false,
                        suppliers = listOf(
                            SupplierDTO(supplierCode = "K123")
                        ),
                        laboratories = listOf()
                    ),
                    PermissionDTO(
                        name = "pqs.pl.write",
                        hasAllSuppliers = false,
                        suppliers = listOf(
                            SupplierDTO(supplierCode = "K123")
                        ),
                        laboratories = listOf()
                    )
                ),
                userType = SUPPLIER
            )
        }

        fun zalando(): UserInfoDTO {
            return UserInfoDTO(
                email = "user@example.com",
                name = "User",
                preferredUsername = "User",
                permissions = listOf(
                    PermissionDTO(
                        name = "pqs.pl.read",
                        hasAllSuppliers = true,
                        suppliers = listOf(),
                        laboratories = listOf()
                    ),
                    PermissionDTO(
                        name = "pqs.pl.write",
                        hasAllSuppliers = true,
                        suppliers = listOf(),
                        laboratories = listOf()
                    )
                ),
                userType = ZALANDO_EMPLOYEE
            )
        }
    }
}

enum class UserType {
    ZALANDO_EMPLOYEE,
    SUPPLIER
}

data class PermissionDTO(
    val name: String,
    val hasAllSuppliers: Boolean?,
    val suppliers: List<SupplierDTO>?,
    val laboratories: List<LaboratoryDTO>?
)

data class SupplierDTO(
    val supplierCode: String
)

data class LaboratoryDTO(
    val laboratoryId: Long
)
