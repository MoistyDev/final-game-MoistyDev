package cegepst.roomClearingGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ZombieRepository implements Iterable<Zombie> {

    private static ZombieRepository instance;
    private final List<Zombie> registeredZombies;

    public static ZombieRepository getInstance() {
        if (instance == null) {
            instance = new ZombieRepository();
        }
        return instance;
    }

    public static void killInstance() {
        ZombieRepository.instance = null;
    }

    public void registerZombie(Zombie zombie) {
        registeredZombies.add(zombie);
    }

    public void unregisterEntity(Zombie zombie) {
        registeredZombies.remove(zombie);
    }

    public int count() {
        return registeredZombies.size();
    }

    private ZombieRepository() {
        registeredZombies = new ArrayList<>();
    }

    @Override
    public Iterator<Zombie> iterator() {
        return registeredZombies.iterator();
    }
}
