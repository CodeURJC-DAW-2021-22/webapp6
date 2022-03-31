package es.webapp6.padelante.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.webapp6.padelante.security.jwt.AuthResponse;
import es.webapp6.padelante.security.jwt.LoginRequest;
import es.webapp6.padelante.security.jwt.UserLoginService;
import es.webapp6.padelante.security.jwt.AuthResponse.Status;

// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.Parameter;
// import io.swagger.v3.oas.annotations.responses.ApiResponses;
// import io.swagger.v3.oas.annotations.responses.ApiResponse;
// import io.swagger.v3.oas.annotations.media.Content;
// import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/auth")
public class LoginRestController {

	@Autowired
	private UserLoginService userService;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@CookieValue(name = "accessToken", required = false) String accessToken,
			@CookieValue(name = "refreshToken", required = false) String refreshToken,
			@RequestBody LoginRequest loginRequest) {
		
		return userService.login(loginRequest, accessToken, refreshToken);
	}

	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refreshToken(
			@CookieValue(name = "refreshToken", required = false) String refreshToken) {

		return userService.refresh(refreshToken);
	}

	// @Operation(summary = "Update user")
	// @ApiResponses(value = {
	// 		@ApiResponse(responseCode = "200", description = "User updated correctly", content = {
	// 				@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
	// 		})
	@PostMapping("/logout")
	public ResponseEntity<AuthResponse> logOut(HttpServletRequest request, HttpServletResponse response) {

		return ResponseEntity.ok(new AuthResponse(Status.SUCCESS, userService.logout(request, response)));
	}
}
