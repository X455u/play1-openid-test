# play1-openid-test
A simple Play application to verify Play framework 1.3.x OpenID bug

Deployed at: http://play1-openid-test.herokuapp.com/

---

In Play framework 1.3.x OpenID [discovery](http://openid.net/specs/openid-authentication-2_0.html#discovery)
fails when the Identifier returns the XRDS document directly.

The bug can be reproduced with this project by using Yahoo as the OpenID Provider and using Yahoo's XRDS Location
as the identifier.
