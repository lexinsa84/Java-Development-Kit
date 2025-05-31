import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimulationResult {
    private int step;
    private boolean isWin;
    private boolean switched;
}