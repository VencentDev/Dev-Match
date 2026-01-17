package com.vinci.devmatch.modules.user.validation.phone;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.vinci.devmatch.modules.user.dto.ContactInfo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DynamicPhoneValidator implements ConstraintValidator<ValidPhoneWithCountry, ContactInfo> {

    @Override
    public boolean isValid(ContactInfo dto, ConstraintValidatorContext context) {
        if (dto == null) return true;

        String phone = dto.getPhone();
        String country = dto.getCountry();

        if (phone == null || phone.isBlank()) {
            return buildFieldViolation(context, "phone", "Phone number is required");
        }

        if (country == null || country.isBlank()) {
            return buildFieldViolation(context, "country", "Country is required for phone validation");
        }

        try {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

            // ✅ Normalize country code to uppercase (e.g., "ph" → "PH")
            String countryCode = country.toUpperCase();

            var parsed = phoneUtil.parse(phone, countryCode);

            if (!phoneUtil.isValidNumber(parsed)) {
                return buildFieldViolation(context, "phone",
                        String.format("Phone number is invalid for %s", country));
            }

            return true;

        } catch (NumberParseException e) {
            String errorMessage = switch (e.getErrorType()) {
                case INVALID_COUNTRY_CODE ->
                        String.format("Invalid country code: %s", country);
                case NOT_A_NUMBER ->
                        "Phone number contains invalid characters";
                case TOO_SHORT_NSN ->
                        "Phone number is too short";
                case TOO_LONG ->
                        "Phone number is too long";
                default ->
                        "Invalid phone number format";
            };

            return buildFieldViolation(context, "phone", errorMessage);
        }
    }

    /**
     * Utility method to attach a validation message to a specific field.
     */
    private boolean buildFieldViolation(ConstraintValidatorContext context, String field, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(field)
                .addConstraintViolation();
        return false;
    }
}
