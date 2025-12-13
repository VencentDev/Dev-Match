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

        // ❌ Phone required but empty → fail with message tied to the 'phone' field
        if (phone == null || phone.isBlank()) {
            return buildFieldViolation(context, "phone", "Phone number is required");
        }

        // ❌ Country required but empty → fail with message tied to the 'country' field
        if (country == null || country.isBlank()) {
            return buildFieldViolation(context, "country", "Country is required for phone validation");
        }

        try {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            var parsed = phoneUtil.parse(phone, country);

            if (!phoneUtil.isValidNumber(parsed)) {
                return buildFieldViolation(context, "phone", "Phone number is invalid for the selected country");
            }

            return true;

        } catch (NumberParseException e) {
            return buildFieldViolation(context, "phone", "Invalid phone number format");
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
