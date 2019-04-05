package io.github.pw2.horarioservice.exceptions;

public class CursoSemHorarioAcademicoException extends Exception {

    public CursoSemHorarioAcademicoException() {
        super("O curso informado nao possui nennum Horario Academico Cadastrado.");
    }

    public CursoSemHorarioAcademicoException(String message) {
        super(message);
    }

}
