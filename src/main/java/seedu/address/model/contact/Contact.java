package seedu.address.model.contact;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Contact in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Contact {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Note note;

    /**
     * Every field must be present and not null except for note.
     */
    public Contact(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Note note) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.note = note;
    }

    /**
     * Every field must be present and not null.
     */
    public Contact(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.note = new Note("");
    }

    public Name getName() {
        return name;
    }
    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Note getNote() {
        return note;
    }

    /**
     * Returns an boolean on whether the contact is a customer based on its tag
     */
    public boolean isCustomer() {
        return tags.stream().anyMatch(tag -> tag.tagName.equals("customer"));
    }

    /**
     * Returns an boolean on whether the contact is a service based on its tag
     */
    public boolean isService() {
        return tags.stream().anyMatch(tag -> tag.tagName.equals("service"));
    }

    /**
     * Returns true if both persons have the same email.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Contact otherContact) {
        if (otherContact == null) {
            return false;
        }
        if (otherContact == this) {
            return true;
        }
        String otherEmail = otherContact.getEmail().toString().toLowerCase();
        String thisEmail = getEmail().toString().toLowerCase();

        return otherEmail.equals(thisEmail);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Contact)) {
            return false;
        }

        Contact otherContact = (Contact) other;
        return name.equals(otherContact.name)
                && phone.equals(otherContact.phone)
                && email.equals(otherContact.email)
                && address.equals(otherContact.address)
                && tags.equals(otherContact.tags)
                && note.equals(otherContact.note);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, note);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("note", note)
                .toString();
    }

}
