# Getting Started

### Reference Documentation

## Getting auth token

### 1. Get access token from 
http://localhost:8080/auth/realms/Keneath

This api returns


{
"realm": "Keneath",
"public_key": "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAham0w+mwELcxL/qKg/F/5gWK2EtKuqLpR9JLVgesrtdORFJE6DqWjLPnBKZgoN3Y03mPaVAyjb5MPnrvxedlD6Mdmqe1FmLaU+6cnH/poQluQbN4oeoVE7bkrjaOPZ/FimsJKvh/lueqiBB+wLypyiGlBUuXz+QRumOrrPGmAhRmgdsVc439ztYgt2J7MqQOHHYvbcx7wjf+nnS/oQS7OjGHx9CZbMJ33bJScJi/Opwo5W4gBbdnGCz+qpNNfZjQ9Putm0E6uiO72OtvH/eAezWgeK4Kc7KF3+1bN1m9BPfEIE9sPl5EJ8j2ntIvTx5CE1FUJg/alE1kTuEJcRZseQIDAQAB",
"token-service": "http://localhost:8080/auth/realms/Keneath/protocol/openid-connect",
"account-service": "http://localhost:8080/auth/realms/Keneath/account",
"tokens-not-before": 0
}

###2. Then, use this api:
http://localhost:8080/auth/realms/Keneath/protocol/openid-connect/token

method: post

The request should have this body in a x-www-form-urlencoded format:
client_id:keneath_client,
username:user1,
password:123456,
grant_type:password
client_secret:816661e5-19cf-4f3d-ae70-7a1491c277b6

The access token should be used in every request to a Keycloak-protected resource by simply placing it in the Authorization header:

headers: {
'Authorization': 'Bearer' + access_token
}

Once the access token has expired, we can refresh it by sending a POST request to the same URL as above, but containing the refresh token instead of username and password:

{
'client_id': 'your_client_id',
'refresh_token': refresh_token_from_previous_request,
'grant_type': 'refresh_token'
}
