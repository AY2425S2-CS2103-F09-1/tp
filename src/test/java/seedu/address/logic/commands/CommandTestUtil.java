package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACCOMMODATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CUSTOMER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITINERARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.model.contact.Note;
import seedu.address.model.trip.Trip;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditTripDescriptorBuilder;
import seedu.address.testutil.TripBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_CUSTOMER = "customer";
    public static final String VALID_TAG_SERVICE = "service";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_CUSTOMER = " " + PREFIX_TAG + VALID_TAG_CUSTOMER;
    public static final String TAG_DESC_SERVICE = " " + PREFIX_TAG + VALID_TAG_SERVICE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditContactCommand.EditPersonDescriptor DESC_AMY;
    public static final EditContactCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_SERVICE).withNote(new Note("")).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_CUSTOMER, VALID_TAG_SERVICE).withNote(new Note("")).build();
    }

    public static final String VALID_TRIP_NAME_PARIS_2025 = "PARIS 2025";
    public static final String TRIP_NAME_DESC_PARIS_2025 = " " + PREFIX_NAME + VALID_TRIP_NAME_PARIS_2025;
    public static final String INVALID_TRIP_NAME_DESC = " " + PREFIX_NAME + "PARIS &&&";

    public static final String VALID_ACCOMMODATION_HOTEL_81 = "Hotel 81";
    public static final String ACCOMMODATION_DESC_HOTEL_81 = " " + PREFIX_ACCOMMODATION
            + VALID_ACCOMMODATION_HOTEL_81;
    public static final String INVALID_ACCOMMODATION_DESC = " " + PREFIX_ACCOMMODATION;

    public static final String VALID_ITINERARY_EAT_BAGUETTES = "Eat baguettes";
    public static final String ITINERARY_DESC_EAT_BAGUETTES = " " + PREFIX_ITINERARY
            + VALID_ITINERARY_EAT_BAGUETTES;
    public static final String INVALID_ITINERARY_DESC = " " + PREFIX_ITINERARY;

    public static final String VALID_TRIP_DATE_2025 = "01/01/2025";
    public static final String TRIP_DATE_DESC_2025 = " " + PREFIX_DATE
            + VALID_TRIP_DATE_2025;
    public static final String INVALID_TRIP_DATE_DESC = " " + PREFIX_DATE + "1/1/202";

    public static final String TRIP_CUSTOMER_DESC_AMY = " " + PREFIX_CUSTOMER_NAME + VALID_NAME_AMY;
    public static final String TRIP_CUSTOMER_DESC_BOB = " " + PREFIX_CUSTOMER_NAME + VALID_NAME_BOB;

    public static final String VALID_NOTE = "Customer prefers window seat";

    public static final String TRIP_NOTE_DESC = " " + PREFIX_NOTE + VALID_NOTE;

    public static final Trip PARIS_2025_TRIP = new TripBuilder()
            .withName(VALID_TRIP_NAME_PARIS_2025)
            .withAccommodation(VALID_ACCOMMODATION_HOTEL_81)
            .withItinerary(VALID_ITINERARY_EAT_BAGUETTES)
            .withDate(VALID_TRIP_DATE_2025)
            .withCustomerNames(VALID_NAME_AMY, VALID_NAME_BOB)
            .withNote(VALID_NOTE)
            .build();

    public static final String VALID_TRIP_NAME_TOKYO_2026 = "TOKYO 2026";
    public static final String TRIP_NAME_DESC_TOKYO_2026 = " " + PREFIX_NAME + VALID_TRIP_NAME_TOKYO_2026;

    public static final String VALID_ACCOMMODATION_RITZ = "Ritz Hotel";
    public static final String ACCOMMODATION_DESC_RITZ = " " + PREFIX_ACCOMMODATION + VALID_ACCOMMODATION_RITZ;

    public static final String VALID_ITINERARY_VISIT_TOWER = "Visit Tokyo Tower";
    public static final String ITINERARY_DESC_VISIT_TOWER = " " + PREFIX_ITINERARY + VALID_ITINERARY_VISIT_TOWER;

    public static final String VALID_TRIP_DATE_2026 = "01/01/2026";
    public static final String TRIP_DATE_DESC_2026 = " " + PREFIX_DATE + VALID_TRIP_DATE_2026;

    public static final String VALID_NOTE_TOKYO = "Customer requested Japanese-speaking guide";
    public static final String TRIP_NOTE_DESC_TOKYO = " " + PREFIX_NOTE + VALID_NOTE_TOKYO;

    public static final EditTripCommand.EditTripDescriptor DESC_PARIS_2025;
    public static final EditTripCommand.EditTripDescriptor DESC_TOKYO_2026;

    static {
        // Add this to the existing static block in CommandTestUtil.java
        DESC_PARIS_2025 = new EditTripDescriptorBuilder().withName(VALID_TRIP_NAME_PARIS_2025)
                .withAccommodation(VALID_ACCOMMODATION_HOTEL_81).withItinerary(VALID_ITINERARY_EAT_BAGUETTES)
                .withDate(VALID_TRIP_DATE_2025).withCustomerNames(VALID_NAME_AMY, VALID_NAME_BOB)
                .withNote(VALID_NOTE).build();

        DESC_TOKYO_2026 = new EditTripDescriptorBuilder().withName(VALID_TRIP_NAME_TOKYO_2026)
                .withAccommodation(VALID_ACCOMMODATION_RITZ).withItinerary(VALID_ITINERARY_VISIT_TOWER)
                .withDate(VALID_TRIP_DATE_2026).withCustomerNames(VALID_NAME_AMY)
                .withNote(VALID_NOTE_TOKYO).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered contact list and selected contact in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Contact> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the contact at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Contact contact = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = contact.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the trip at the given {@code targetIndex} in the
     * {@code model}'s trip book.
     */
    public static void showTripAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTripList().size());

        Trip trip = model.getFilteredTripList().get(targetIndex.getZeroBased());
        String tripName = trip.getName().toString();
        final String[] splitName = tripName.split("\\s+");
        model.updateFilteredTripList(t -> StringUtil.containsWordIgnoreCase(t.getName().toString(), splitName[0]));

        assertEquals(1, model.getFilteredTripList().size());
    }

}
