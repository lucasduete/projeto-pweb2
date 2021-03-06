package io.github.pw2.horarioservice.valueObjects;

import com.google.common.collect.ImmutableList;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class HorarioAcademicoVO implements Serializable {

    private int id;
    private List<DiaLetivoVO> diasLetivos;

    {
        this.diasLetivos = new ArrayList<>();
    }

    public List<DiaLetivoVO> getDiasLetivos() {
        return ImmutableList.copyOf(this.diasLetivos);
    }

    public void addDiaLetivo(@NotNull final DiaLetivoVO diaLetivo) {
        this.diasLetivos.add(diaLetivo);
    }

}
