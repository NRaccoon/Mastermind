package mastermind;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
/**
 * Contain pins possible value.
 */
public class PinStruct {
    private int white;
    private int black;
}
