package CounterStriker.models.guns;

public abstract class GunImpl implements Gun {
    private String name;
    private int bulletsCount;

    public GunImpl(String name, int bulletsCount) {
        this.setName(name);
        this.setBulletsCount(bulletsCount);
    }

    private void setBulletsCount(int bulletsCount) {
        this.bulletsCount = bulletsCount;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getBulletsCount() {
        return 0;
    }

    @Override
    public int fire() {
        return 0;
    }
}
