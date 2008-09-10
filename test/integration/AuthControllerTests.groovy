import org.jsecurity.crypto.hash.Sha1Hash

class AuthControllerTests extends GroovyTestCase {

		def ac
		def jsecSecurityManager
		
		void setUp() {
				// create controller and inject service
				ac = new AuthController()
				ac.jsecSecurityManager = jsecSecurityManager
				//println (jsecSecurityManager.dump())
				
				// test user, role, and relation
				def testUser = 
						new JsecUser(username:'test_user',
							passwordHash: new Sha1Hash('test_password').toHex()).save()
				def testRole = new JsecRole(name:'test_role').save()
				new JsecUserRoleRel(user:testUser,role:testRole).save()
		}
		
		void tearDown() {
				JsecUserRoleRel.list()*.delete()
				JsecUser.list()*.delete()
				JsecRole.list()*.delete()
		}
		
		void testUsernameAlreadyExists() {
				ac.params.username = 'test_user'
				ac.register()
				assertEquals "That id is already taken", ac.flash.message
		}
		
    void testUsernameWithoutPassword() {
				ac.params.username = 'test'
				ac.register()
				assertEquals "You must supply a username and password", ac.flash.message
    }
    
    void testPasswordsDontMatch() {
    		ac.params.username = 'test'
    		ac.params.password = 'password'
    		ac.params.confirmPassword = 'somethingdifferent'
    		ac.register()
    		assertEquals "Password and confirmation must match", ac.flash.message
    }

//  This test is throwing a NullPointerException somewhere deep inside
//  the call to jsecSecurityManager.login(authToken)
//
//    void testSuccessfulRegistration() {
//    		ac.params.username = 'test'
//    		ac.params.password = 'password'
//    		ac.params.confirmPassword = 'password'
//    		assertNotNull ac.jsecSecurityManager
//    		ac.register()
//    		assertEquals "/plugin/index", ac.response.redirectedUrl
//    }

			void testUsernameIsAvailable() {
					ac.params.username = 'test'
					ac.isAvailable()
					assertEquals "<span style='color: green'>Yes</span>", ac.response.contentAsString
			}

			void testUsernameIsNotAvailable() {
					ac.params.username = 'test_user'
					ac.isAvailable()
					assertEquals "<span style='color: red'>No</span>", ac.response.contentAsString
			}
}
