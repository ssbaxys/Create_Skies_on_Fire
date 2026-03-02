import json
import os

def create_model(name, elements):
    model = {
        "parent": "block/block",
        "textures": {
            "all": f"create_skies_on_fire:block/{name}",
            "particle": f"create_skies_on_fire:block/{name}"
        },
        "elements": []
    }
    
    for e in elements:
        x1, y1, z1 = e["from"]
        x2, y2, z2 = e["to"]
        element = {
            "from": e["from"],
            "to": e["to"],
            "faces": {
                "down":  {"uv": [x1, z1, x2, z2], "texture": "#all"},
                "up":    {"uv": [x1, z1, x2, z2], "texture": "#all"},
                "north": {"uv": [x1, 16-y2, x2, 16-y1], "texture": "#all"},
                "south": {"uv": [x1, 16-y2, x2, 16-y1], "texture": "#all"},
                "west":  {"uv": [z1, 16-y2, z2, 16-y1], "texture": "#all"},
                "east":  {"uv": [z1, 16-y2, z2, 16-y1], "texture": "#all"}
            }
        }
        model["elements"].append(element)
        
    path = f"src/main/resources/assets/create_skies_on_fire/models/block/{name}.json"
    with open(path, "w") as f:
        json.dump(model, f, indent=2)

base_elements = [
    { "from": [1, 0, 1], "to": [15, 16, 15] }
]
create_model("rocket_base", base_elements)

thruster_elements = [
  { "from": [4, 0, 4], "to": [12, 4, 12] },
  { "from": [3, 4, 3], "to": [13, 10, 13] },
  { "from": [2, 10, 2], "to": [14, 16, 14] }
]
create_model("rocket_thruster", thruster_elements)

hull_elements = [
  { "from": [2, 0, 0], "to": [14, 16, 16] },
  { "from": [0, 0, 2], "to": [16, 16, 14] }
]
create_model("rocket_hull", hull_elements)

nose_elements = [
  { "from": [2, 0, 2], "to": [14, 6, 14] },
  { "from": [4, 6, 4], "to": [12, 10, 12] },
  { "from": [6, 10, 6], "to": [10, 14, 10] },
  { "from": [7, 14, 7], "to": [9, 16, 9] }
]
create_model("rocket_nose_cone", nose_elements)

print("JSON models generated successfully.")
