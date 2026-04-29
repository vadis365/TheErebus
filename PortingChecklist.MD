### mc1.12 to 26.1 Porting Checklist

The port to 26.1 (Minecraft 1.21.x) is well underway. Most world generation, core entities, and complex block renderers are already in place. However, several functional systems, specific mobs, and utility blocks remain to be ported.

---

#### 🐜 Entities & Mobs
- [ ] **Mobs:** `Blood Snail`, `Leech`, `Armchair Mount`.
- [ ] **Functional Entities:** `Animated Chest`, `Animated Bamboo Crate`.
- [ ] **Projectiles:** `Wasp Dagger`, `Web Sling`, `Goo Ball`, `Woodlouse Ball`, `Spore Ball`, `Gas Vent`, `Thrown Sand`.
- [ ] **Internal/Misc:** `Umber Golem Idol`, `Fire Resistent Item`.

#### 🧱 Blocks & Functional Logic
- [ ] **Functional Blocks:**
    - `Smoothie Maker` (Block and `TileEntitySmoothieMaker` logic).
    - `Titan Chest` & `Titan Ender Chest`.
    - `Temple Teleporter`.
- [ ] **Environment & Decoration:**
    - `Giant Flower` & `Giant Flower Stigma` (Full block variants).
    - `Hollow Log` (Functional/Generation).
    - `Small Plant`, `Wall Plants`, `Planted Flower`.
- [ ] **Ores & Materials:**
    - `Aluminium Ore` (Block registration).
    - `Red Gem` (Block registration).
    - `Mir Brick`, `Mud Brick` (Block forms).
- [ ] **Construction:**
    - `Petrified Wood` variants (Doors, Stairs, Slabs).
    - `Amber Door`.

#### 🗡️ Items & Equipment
- [ ] **Consumables:** `Smoothie` (Item system and effects).
- [ ] **Seeds:** `Flower Seed`.

#### 🧪 Systems & Functionality
- [ ] **GUIs & Screens:** The standard `erebus.client.gui` package is missing. UI for the `Liquifier`, `Blender`, `Umber Furnace`, and `Altars` needs verification or porting.
- [ ] **Capabilities:** Verify porting of 1.12-style capabilities (e.g., for Glider flight, special item logic) to the 1.21 `Data Component` or `Capability` systems.
- [ ] **Fluids:** `Formic Acid` and `Honey` fluid registration and world interaction logic.
- [ ] **Special Systems:** `Preserved Block` (Entity preservation in Amber/Resin) logic.

#### 🌍 Worldgen (Refinement)
- [ ] **Features:** Verify if `Algae` and `Big Logs` (horizontal log variations) are generating correctly.
- [ ] **Structures:** Ensure Loot Tables are fully populated for the JSON-based structures (`Antlion Dungeon`, `Wasp Dungeon`, etc.).

---

### Summary of Progress
- **Worldgen:** ~95% Complete (All major biomes and structures present).
- **Blocks:** ~80% Complete (Most visual blocks ported, some functional/storage ones missing).
- **Entities:** ~70% Complete (Core mobs present, projectiles and variants missing).
- **Functionality:** ~60% Complete (BlockEntities are ported, but UIs and specialized item systems are pending).
