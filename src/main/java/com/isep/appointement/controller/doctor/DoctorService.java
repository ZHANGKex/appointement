/*
package com.isep.appointement.controller.doctor;

import com.isep.appointement.Repository.PatientRepository;
import com.isep.appointement.model.ConfirmationToken;
import com.isep.appointement.controller.ConfirmToken.ConfirmationTokenService;
import com.isep.appointement.controller.email.EmailSender;
import com.isep.appointement.model.Patient;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DoctorService implements UserDetailsService {

    private final PatientRepository patientRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private EmailSender emailSender;
    public static String LoginErrorMsg;


    public List<Patient> getAllPatient() {

        return patientRepository.findAll();
    }
    public Patient getPatientById(Long id) {

        return patientRepository.findById(id).get();
    }
    public Patient getPatientByEmail(String email) {
        Optional<Patient> patientsByEmail =  patientRepository.findPatientsByMail(email.toLowerCase(Locale.ROOT));
        if(!patientsByEmail.isPresent() && !patientsByEmail.get().getEnabled()){
            throw new IllegalStateException("patient email does not exist ");
        }
        return patientRepository.findPatientsByMail(email.toLowerCase(Locale.ROOT)).get();
    }
    public Patient getPatientByPhone(String telephone) {
        Optional<Patient> patientsByPhone =  patientRepository.findPatientsByPhone(telephone);
        if(!patientsByPhone.isPresent()){
            throw new IllegalStateException("patient phone number does not exist ");
        }
        return patientRepository.findPatientsByMail(telephone).get();
    }

    public String addPatient(Patient patient) {
        Optional<Patient> patientsByMail =  patientRepository.findPatientsByMail(patient.getMail());
        if(patientsByMail.isPresent() && !patientsByMail.get().getEnabled()){
            throw new IllegalStateException("email existed ");

        }
        LocalDate birthdate = patient.getBirthday();
        if(birthdate == null){
            birthdate = LocalDate.now();
        }
        int age = Period.between(birthdate,LocalDate.now()).getYears();
        patient.setAge(age);
        patient.setPassword(new BCryptPasswordEncoder().encode(patient.getPassword()));
        patientRepository.save(patient);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2),
                patient
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        //TODO: send email;
        String link = "http://localhost:8080/register/confirm?token=" + token;
        emailSender.send(
                patient.getMail(),
                buildEmail(patient.getName(), link));
        return token;
    }
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        enableUser(confirmationToken.getPatient().getMail());
        return "redirect:/home";
    }
    public void editPatient(Patient patient) {
        patientRepository.save(patient);
    }
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient patient = patientRepository.findPatientsByMail(username).get();
        if(patient ==null){
            LoginErrorMsg = "This email doesn't exist";
            throw new UsernameNotFoundException("Invalid email or password");
        }
        else if(!patient.isEnabled()){
            LoginErrorMsg = "This email hasn't activated";
            throw new UsernameNotFoundException("This email hasn't activated");
        }

        return new org.springframework.security.core.userdetails.User(patient.getMail(),
                patient.getPassword(),patient.isEnabled(),patient.isAccountNonExpired(),
                patient.isCredentialsNonExpired(),patient.isAccountNonLocked(),
                patient.getAuthorities());

    }

*/
/*    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(){
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Roles.Patient.name());
        return Collections.singletonList(authority);
    }*//*

*/
/*    public int enableAppointment(String email) {
        return patientRepository.enablePatient(email);
    }*//*


    */
/*private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 2 hours. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }*//*

}
*/
