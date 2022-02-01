package com.training.pms.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("credentials")
@Component
public class Credentials {
			private String username;
			private String password;
			private String secretquestion;
			private String secretanswer;
			
			public Credentials() {
				// TODO Auto-generated constructor stub
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

			public String getSecretquestion() {
				return secretquestion;
			}

			public void setSecretquestion(String secretquestion) {
				this.secretquestion = secretquestion;
			}

			public String getSecretanswer() {
				return secretanswer;
			}

			public void setSecretanswer(String secretanswer) {
				this.secretanswer = secretanswer;
			}

			@Override
			public String toString() {
				return "Credentials [username=" + username + ", password=" + password + ", secretquestion="
						+ secretquestion + ", secretanswer=" + secretanswer + "]";
			}
			
}
