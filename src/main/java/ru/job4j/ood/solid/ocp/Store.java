package ru.job4j.ood.solid.ocp;

public class Store {

    public void getData(MemoryStore memoryStore) {
        /*
        Получаем данные из памяти. Если понадобится сменить источник
        данных то придется менять тип входных параметров и переписывать
        логику метода.
         */
    }

    static class MemoryStore {
    }
}

