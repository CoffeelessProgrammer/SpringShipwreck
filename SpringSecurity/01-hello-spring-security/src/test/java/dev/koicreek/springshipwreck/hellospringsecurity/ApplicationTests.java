package dev.koicreek.springshipwreck.hellospringsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	@Autowired
	MockMvc mvc;

	//#region AnonymousUser

	@Test
	@WithAnonymousUser
	void anonymousUser_VERIFY_CanAccessBaseURL() throws Exception {
		mvc.perform(get("/"))
				.andExpect(status().isOk());

		mvc.perform(get("/public"))
				.andExpect(status().isOk());
	}

	@Test
	@WithAnonymousUser
	void anonymousUser_WHEN_EndpointSecured_THEN_Unauthorized() throws Exception {
		mvc.perform(get("/trade"))
				.andExpect(status().isUnauthorized());

		mvc.perform(get("/heal"))
				.andExpect(status().isUnauthorized());

		mvc.perform(get("/service"))
				.andExpect(status().isUnauthorized());
	}

	//#endregion

	//#region GenericUser

	@Test
	@WithUserDetails("brock")
	void genericUser_VERIFY_CanAccessTradeEndpoint() throws Exception {
		mvc.perform(get("/trade"))
				.andExpect(status().isOk());

		mvc.perform(get("/public"))
				.andExpect(status().isOk());

		mvc.perform(get("/"))
				.andExpect(status().isForbidden());
	}

	@Test
	@WithUserDetails("brock")
	void genericUser_WHEN_EndpointRequiresSpecialRole_THEN_Forbidden() throws Exception {
		mvc.perform(get("/heal"))
				.andExpect(status().isForbidden());

		mvc.perform(get("/service"))
				.andExpect(status().isForbidden());
	}

	//#endregion

	//#region NurseRole

	@Test
	@WithUserDetails("nursejoy")
	void userNurseRole_VERIFY_CanAccessAllEndpoints() throws Exception {
		mvc.perform(get("/heal"))
				.andExpect(status().isOk());

		mvc.perform(get("/service"))
				.andExpect(status().isOk());

		mvc.perform(get("/trade"))
				.andExpect(status().isOk());

		mvc.perform(get("/public"))
				.andExpect(status().isOk());

		mvc.perform(get("/"))
				.andExpect(status().isForbidden());
	}

	//#endregion

	//#region AdminRole

	@Test
	@WithUserDetails("admin")
	void userAdminRole_VERIFY_CanAccessTradeAndServiceEndpoints() throws Exception {
		mvc.perform(get("/trade"))
				.andExpect(status().isOk());

		mvc.perform(get("/service"))
				.andExpect(status().isOk());

		mvc.perform(get("/public"))
				.andExpect(status().isOk());

		mvc.perform(get("/"))
				.andExpect(status().isForbidden());
	}

	@Test
	@WithUserDetails("admin")
	void userAdminRole_WHEN_EndpointRequiresNurseRole_THEN_Forbidden() throws Exception {
		mvc.perform(get("/heal"))
				.andExpect(status().isForbidden());
	}

	//#endregion

}
